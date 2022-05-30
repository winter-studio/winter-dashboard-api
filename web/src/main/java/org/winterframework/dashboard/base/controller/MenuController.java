package org.winterframework.dashboard.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "菜单模块")
@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    @ApiOperation("获取菜单树")
    @GetMapping("/tree")
    public ApiRes<List<MenuTree>> getTree() {
        return ApiRes.success(menuService.getTree());
    }

    @ApiOperation("获取菜单")
    @GetMapping("/{id}")
    public ApiRes<Menu> getById(@PathVariable Integer id) {
        return ApiRes.success(menuService.getById(id));
    }
}
