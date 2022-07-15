package org.winterframework.dashboard.api.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.winterframework.dashboard.api.user.entity.Role;
import org.winterframework.dashboard.api.user.mapper.RoleMapper;
import org.winterframework.dashboard.web.model.SelectOption;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Service
@RequiredArgsConstructor
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IService<Role> {

    private final UserRoleService userRoleService;
    private final RoleMenuService roleMenuService;

    public List<SelectOption> getRoleOptions() {
        return baseMapper.queryRoleOptions();
    }

    @Transactional
    public void deleteRoles(List<Integer> ids) {
        baseMapper.deleteBatchIds(ids);
        userRoleService.deleteByRoleIds(ids);
        roleMenuService.deleteByRoleIds(ids);
    }

}
