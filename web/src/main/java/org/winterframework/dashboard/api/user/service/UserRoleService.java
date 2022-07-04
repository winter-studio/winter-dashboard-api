package org.winterframework.dashboard.api.user.service;

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
}
