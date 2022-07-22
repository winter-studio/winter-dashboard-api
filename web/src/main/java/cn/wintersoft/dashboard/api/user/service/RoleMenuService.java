package cn.wintersoft.dashboard.api.user.service;

import cn.wintersoft.dashboard.api.user.mapper.RoleMenuMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wintersoft.dashboard.api.user.entity.RoleMenu;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IService<RoleMenu> {

    public void deleteByRoleIds(List<Integer> ids) {
        this.remove(Wrappers.lambdaQuery(RoleMenu.class).in(RoleMenu::getRoleId, ids));
    }

    public List<Integer> getRoleMenuIds(Integer id) {
        return baseMapper.getMenuIdsByRoleId(id);
    }

    @Transactional
    public void updateRoleMenus(Integer id, List<Integer> ids) {
        remove(Wrappers.lambdaQuery(RoleMenu.class).eq(RoleMenu::getRoleId, id));
        if (CollectionUtils.isNotEmpty(ids)) {
            List<RoleMenu> roleMenus = ids.stream().map(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(id);
                roleMenu.setMenuId(menuId);
                return roleMenu;
            }).toList();
            saveBatch(roleMenus);
        }
    }
}
