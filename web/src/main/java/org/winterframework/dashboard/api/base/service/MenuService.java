package org.winterframework.dashboard.api.base.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.winterframework.dashboard.api.base.entity.Menu;
import org.winterframework.dashboard.api.base.mapper.MenuMapper;
import org.winterframework.dashboard.api.base.model.data.MenuTree;
import org.winterframework.dashboard.api.base.model.request.MoveMenuRequest;
import org.winterframework.dashboard.api.base.utils.UserMenuTreeBuilder;
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
        Integer maxSort = this.baseMapper.getMaxSortInDirectory(menu.getParentId());
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
            if (this.hasChildren(id)) {
                Menu byId = this.getById(id);
                String title = byId.getTitle();
                throw new ApiFailureException(String.format("菜单【%s】下有子菜单，不能删除，请先移动或删除子菜单", title));
            }
        }
        return this.removeBatchByIds(ids);
    }

    public boolean hasChildren(Integer id) {
        return this.count(Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId, id)) > 0;
    }

    @Transactional
    public boolean moveMenu(Integer target, MoveMenuRequest request) {
        // get the target menu
        Menu targetMenu = this.getById(target);
        if (targetMenu == null) {
            throw new ApiFailureException("目标菜单不存在");
        }

        // get the relative menu
        Integer relative = request.relative();
        Menu relativeMenu = this.getById(relative);
        if (relativeMenu == null) {
            throw new ApiFailureException("相对菜单不存在");
        }


        if (request.position() == MoveMenuRequest.Position.INSIDE) {
            // move the menu to into the relative menu
            Integer maxSort = this.baseMapper.getMaxSortInDirectory(relative);
            targetMenu.setParentId(relative);
            targetMenu.setSort(maxSort + 1);
        } else {
            boolean moveWithRelativeMenu = request.position() == MoveMenuRequest.Position.BEFORE;


            // get the same level menus of the relative menu
            this.baseMapper.moveEachMenuDownAfterRelative(relativeMenu.getParentId(), relativeMenu.getSort(),
                    moveWithRelativeMenu);

            // insert the target menu after/before the relative menu
            targetMenu.setParentId(relativeMenu.getParentId());
            targetMenu.setSort(relativeMenu.getSort() + (moveWithRelativeMenu ? 0 : 1));
        }
        return this.updateById(targetMenu);
    }
}
