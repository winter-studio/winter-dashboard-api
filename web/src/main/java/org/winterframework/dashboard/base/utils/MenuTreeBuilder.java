package org.winterframework.dashboard.base.utils;

import org.winterframework.dashboard.base.entity.Menu;
import org.winterframework.dashboard.base.model.data.MenuTree;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuTreeBuilder {

    private List<Menu> menus;

    public MenuTreeBuilder(List<Menu> menus) {
        this.menus = menus;
    }

    public List<MenuTree> build() {
        List<MenuTree> menuTrees;
        Map<Integer, MenuTree> menuMap = menus.stream().collect(Collectors.toMap(Menu::getId, MenuTree::new));
        //TODO menu builer
        return null;
    }
}
