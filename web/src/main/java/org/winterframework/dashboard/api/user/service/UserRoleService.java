package org.winterframework.dashboard.api.user.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.winterframework.dashboard.api.user.entity.UserRole;
import org.winterframework.dashboard.api.user.mapper.UserRoleMapper;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> implements IService<UserRole> {

    public List<String> getUserRoles(Long userId) {
        return this.baseMapper.getUserRoles(userId);
    }

    public void bindUserRole(Long userId, Integer roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        this.baseMapper.insert(userRole);
    }

    public void addUserRoles(Long id, List<String> roles) {
        this.baseMapper.removeUserRoles(id);
        if (CollectionUtils.isNotEmpty(roles)) {
            List<UserRole> userRoles = roles.stream().map(role -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(id);
                userRole.setRoleId(Integer.parseInt(role));
                return userRole;
            }).toList();
            this.saveBatch(userRoles);
        }
    }

    public void deleteByRoleIds(List<Integer> ids) {
        this.remove(Wrappers.lambdaQuery(UserRole.class).in(UserRole::getRoleId, ids));
    }
}
