package org.winterframework.dashboard.api.base.utils;

import org.winterframework.dashboard.api.base.entity.Menu;
import org.winterframework.dashboard.api.base.model.data.MenuTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMenuTreeBuilder {

    private final List<Menu> menus;

    public UserMenuTreeBuilder(List<Menu> menus) {
        this.menus = menus;
    }

    public List<MenuTree> build() {
        List<MenuTree> menuTrees;
        Set<Integer> roots = new HashSet<>();
        Map<Integer, MenuTree> menuMap = menus.stream().collect(Collectors.toMap(Menu::getId, MenuTree::new));
        for (Map.Entry<Integer, MenuTree> e : menuMap.entrySet()) {
            MenuTree menuTree = e.getValue();
            Integer parentId = menuTree.getParentId();
            if (parentId != null) {
                menuMap.get(parentId).getChildren().add(menuTree);
            } else {
                roots.add(menuTree.getId());
            }
        }
        menuMap.values().removeIf(menuTree -> !roots.contains(menuTree.getId()));
        menuTrees = new ArrayList<>(menuMap.values());
        //sort menuTrees and its children
        sortMenuTree(menuTrees);
        return menuTrees;
    }

    private void sortMenuTree(List<MenuTree> menuTrees) {
        Collections.sort(menuTrees);
        menuTrees.forEach(menuTree -> {
            if (menuTree.getChildren().size() > 1) {
                sortMenuTree(menuTree.getChildren());
            }
        });
    }
}
