package org.winterframework.dashboard.base.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.base.entity.Menu;
import org.winterframework.dashboard.base.model.data.MenuTree;
import org.winterframework.dashboard.base.service.MenuService;
import org.winterframework.dashboard.web.model.ApiRes;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Tag(name = "菜单模块")
@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    @Operation(summary = "获取菜单树")
    @GetMapping("/tree")
    public ApiRes<List<MenuTree>> getTree() {
        return ApiRes.success(menuService.getTree());
    }

    @Operation(summary = "获取菜单")
    @GetMapping("/{id}")
    public ApiRes<Menu> getById(@PathVariable Integer id) {
        return ApiRes.success(menuService.getById(id));
    }
}
