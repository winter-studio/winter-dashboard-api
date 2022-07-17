package org.winterframework.dashboard.api.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.winterframework.dashboard.api.user.entity.Menu;
import org.winterframework.dashboard.api.user.entity.User;
import org.winterframework.dashboard.api.user.mapper.MenuMapper;
import org.winterframework.dashboard.api.user.mapper.UserMapper;
import org.winterframework.dashboard.api.user.model.data.MenuTree;
import org.winterframework.dashboard.api.user.model.request.AdminUserPageReq;
import org.winterframework.dashboard.api.user.model.request.UserEditForm;
import org.winterframework.dashboard.api.user.model.request.UserPassword;
import org.winterframework.dashboard.api.user.model.request.UserProfile;
import org.winterframework.dashboard.api.user.model.response.AdminUserForm;
import org.winterframework.dashboard.api.user.model.response.AdminUserPageItem;
import org.winterframework.dashboard.api.user.model.response.UserInfoResponse;
import org.winterframework.dashboard.api.user.utils.UserMenuTreeBuilder;
import org.winterframework.dashboard.minio.MinioManager;
import org.winterframework.dashboard.minio.StorePath;
import org.winterframework.dashboard.security.utils.SecurityUtils;
import org.winterframework.dashboard.web.model.PageRes;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Kyun
 * @since 2022-05-23
 */
@Service
@RequiredArgsConstructor
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {

    private final PasswordEncoder passwordEncoder;
    private final MenuMapper menuMapper;
    private final UserRoleService userRoleService;
    private final MinioManager minioManager;
    private final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9_]{6,32}$");

    public User getByUsername(String username) {
        LambdaQueryWrapper<User> query = Wrappers.<User>lambdaQuery().eq(User::getUsername, username);
        return this.getOne(query);
    }

    public List<MenuTree> getCurrentUserMenuTree() {
        Long userId = SecurityUtils.getUserId();
        List<Menu> menus = menuMapper.getMenusByUserId(userId);
        UserMenuTreeBuilder builder = new UserMenuTreeBuilder(menus);
        return builder.build();
    }

    public UserInfoResponse getCurrentUserInfo() {
        Long userId = SecurityUtils.getUserId();
        List<String> roles = userRoleService.getUserRoles(userId);
        User user = this.getById(userId);
        return new UserInfoResponse(user, roles);
    }


    public PageRes<AdminUserPageItem> getUserPagingList(AdminUserPageReq req) {
        Page<?> page = req.toPage();

        return PageRes.of(baseMapper.pagingQueryUsers(page, req));
    }

    public AdminUserForm getUserForm(Long id) {
        return this.baseMapper.queryUserFormById(id);
    }

    public String uploadUserAvatar(MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        return minioManager.putFileAndGetUrl(file.getInputStream(), file.getSize(), name, StorePath.UserProfile);
    }

    public void changeUserStatus(Long id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        this.updateById(user);
    }

    @Transactional
    public Long addUser(UserEditForm form) {
        User user = getUserFromForm(form);
        this.save(user);
        userRoleService.addUserRoles(user.getId(), form.getRoles());
        return user.getId();
    }

    @NotNull
    private User getUserFromForm(UserEditForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        if (form.getPassword() != null) {
            if (!passwordPattern.matcher(form.getPassword()).matches()) {
                throw new IllegalArgumentException("密码格式不正确");
            }
            user.setPassword(passwordEncoder.encode(form.getPassword()));
        }
        user.setStatus(form.getStatus());
        user.setAvatar(form.getAvatar());
        user.setMobile(form.getMobile());
        user.setNickname(form.getNickname());
        return user;
    }

    @Transactional
    public void editUser(Long id, UserEditForm form) {
        User user = getUserFromForm(form);
        user.setId(id);
        this.updateById(user);
        userRoleService.addUserRoles(user.getId(), form.getRoles());
    }

    public void updateUserProfile(UserProfile profile) {
        User user = new User();
        user.setId(SecurityUtils.getUserId());
        user.setAvatar(profile.getAvatar());
        user.setNickname(profile.getNickname());
        this.updateById(user);
    }

    public void updateUserPassword(UserPassword password) {
        User user = new User();
        if (password.getOldPassword() != null) {
            if (!passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
                throw new IllegalArgumentException("当前密码不正确");
            }
        }
        if (!passwordPattern.matcher(password.getNewPassword()).matches()) {
            throw new IllegalArgumentException("密码格式不正确");
        }
        user.setId(SecurityUtils.getUserId());
        user.setPassword(passwordEncoder.encode(password.getNewPassword()));
        this.updateById(user);
    }
}
