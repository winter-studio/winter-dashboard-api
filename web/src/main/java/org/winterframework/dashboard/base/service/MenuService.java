package org.winterframework.dashboard.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.winterframework.dashboard.base.entity.Menu;
import org.winterframework.dashboard.base.mapper.MenuMapper;
import org.winterframework.dashboard.base.model.data.MenuTree;
import org.winterframework.dashboard.base.utils.UserMenuTreeBuilder;
import org.winterframework.dashboard.web.exception.ApiFailureException;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IService<Menu> {

    public List<MenuTree> getTree() {
        UserMenuTreeBuilder builder = new UserMenuTreeBuilder(this.list());
        return builder.build();
    }

    public boolean addMenu(Menu menu) {
        // get the max sort below the parent menu
        Integer maxSort = this.baseMapper.getMaxSort(menu.getParentId());
        menu.setSort(maxSort + 1);
        return this.save(menu);
    }

    public boolean updateMenu(Integer id, Menu menu) {
        menu.setId(id);
        return this.updateById(menu);
    }

    public boolean deleteMenus(List<Integer> ids) {
        // check if the menu has children
        for (Integer id : ids) {
            if (this.baseMapper.hasChildren(id)) {
                Menu byId = this.getById(id);
                String title = byId.getTitle();
                throw new ApiFailureException(String.format("菜单【%s】下有子菜单，不能删除，请先移动或删除子菜单", title));
            }
        }
        return this.removeBatchByIds(ids);
    }
}
