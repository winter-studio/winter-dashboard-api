package org.winterframework.dashboard.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.winterframework.dashboard.base.entity.Menu;
import org.winterframework.dashboard.base.mapper.MenuMapper;
import org.winterframework.dashboard.base.model.data.MenuTree;
import org.winterframework.dashboard.base.utils.UserMenuTreeBuilder;

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
        boolean save = this.save(menu);
        this.baseMapper.updateSortToTheEnd(menu.getParentId());
        return save;
    }

    public boolean updateMenu(Integer id, Menu menu) {
        menu.setId(id);
        return this.updateById(menu);
    }
}
