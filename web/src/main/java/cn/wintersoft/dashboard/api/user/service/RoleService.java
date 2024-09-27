package cn.wintersoft.dashboard.api.user.service;

import cn.wintersoft.dashboard.api.user.entity.Role;
import cn.wintersoft.dashboard.api.user.mapper.RoleMapper;
import cn.wintersoft.dashboard.api.user.model.request.RolePageReq;
import cn.wintersoft.dashboard.web.model.PageRes;
import cn.wintersoft.dashboard.web.model.SelectOption;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public PageRes<Role> getRolePagingList(RolePageReq req) {
        Page<Role> page = req.toPage();
        LambdaQueryWrapper<Role> qw =
            Wrappers.<Role>lambdaQuery()
                    .like(req.getRoleCode() != null, Role::getCode, req.getRoleCode())
                    .like(req.getRoleName() != null, Role::getName, req.getRoleName());
        return PageRes.of(this.page(page, qw));
    }
}
