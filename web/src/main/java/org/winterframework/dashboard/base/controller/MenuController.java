package org.winterframework.dashboard.base.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.base.entity.Menu;
import org.winterframework.dashboard.base.model.data.MenuTree;
import org.winterframework.dashboard.base.model.request.MoveMenuRequest;
import org.winterframework.dashboard.base.service.MenuService;
import org.winterframework.dashboard.web.model.ApiRes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    @Operation(summary = "新增菜单")
    @PostMapping()
    public ApiRes<Menu> addMenu(@Validated @RequestBody Menu menu) {
        boolean succeeded = menuService.addMenu(menu);
        return ApiRes.<Menu>baseOn(succeeded)
                     .successThen().message("新增成功").data(menu)
                     .failureThen().message("新增失败")
                     .get();
    }

    @Operation(summary = "更新菜单")
    @PutMapping("/{id}")
    public ApiRes<Menu> updateMenu(@PathVariable Integer id, @RequestBody Menu menu) {
        boolean succeeded = menuService.updateMenu(id, menu);
        return ApiRes.<Menu>baseOn(succeeded)
                     .successThen().message("保存成功").data(menu)
                     .failureThen().message("保存失败")
                     .get();
    }

    @Operation(summary = "批量删除菜单")
    @DeleteMapping("/{id}")
    public ApiRes<Boolean> deleteMenu(@PathVariable Integer id) {
        boolean succeeded = menuService.deleteMenus(Collections.singletonList(id));
        return ApiRes.<Boolean>baseOn(succeeded)
                     .successThen().message("删除成功")
                     .failureThen().message("删除失败")
                     .get();
    }

    @Operation(summary = "批量删除菜单")
    @DeleteMapping
    public ApiRes<Boolean> deleteMenus(@RequestBody Map<String,List<Integer>> body) {
        boolean succeeded = menuService.deleteMenus(body.get("ids"));
        return ApiRes.<Boolean>baseOn(succeeded)
                     .successThen().message("删除成功")
                     .failureThen().message("删除失败")
                     .get();
    }

    @Operation(summary = "移动菜单")
    @PutMapping("/{id}/position")
    public ApiRes<Boolean> moveMenu(@PathVariable Integer id, @RequestBody MoveMenuRequest request) {
        boolean succeeded = menuService.moveMenu(id, request);
        return ApiRes.<Boolean>baseOn(succeeded)
                     .successThen().message("移动成功")
                     .failureThen().message("移动失败")
                     .get();
    }

}
