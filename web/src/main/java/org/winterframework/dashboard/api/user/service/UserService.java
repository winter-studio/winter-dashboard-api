package org.winterframework.dashboard.api.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.winterframework.dashboard.api.user.entity.Menu;
import org.winterframework.dashboard.api.user.entity.User;
import org.winterframework.dashboard.api.user.mapper.MenuMapper;
import org.winterframework.dashboard.api.user.mapper.UserMapper;
import org.winterframework.dashboard.api.user.model.data.MenuTree;
import org.winterframework.dashboard.api.user.model.request.AdminUserPageReq;
import org.winterframework.dashboard.api.user.model.request.CreateUserReq;
import org.winterframework.dashboard.api.user.model.response.AdminUserForm;
import org.winterframework.dashboard.api.user.model.response.AdminUserPageItem;
import org.winterframework.dashboard.api.user.model.response.CreateUserRes;
import org.winterframework.dashboard.api.user.model.response.UserInfoResponse;
import org.winterframework.dashboard.api.user.utils.UserMenuTreeBuilder;
import org.winterframework.dashboard.security.utils.SecurityUtils;
import org.winterframework.dashboard.web.exception.ApiFailureException;
import org.winterframework.dashboard.web.model.PageRes;

import java.util.List;

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

    public CreateUserRes createUser(CreateUserReq req) {
        User user = new User();
        user.setUsername(req.username());
        user.setPassword(passwordEncoder.encode(req.password()));
        boolean saved;
        try {
            saved = this.save(user);
        } catch (Exception e) {
            throw new ApiFailureException("用户名已存在", e);
        }
        return saved ? new CreateUserRes(user.getId(), user.getUsername()) : null;
    }

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
}
