package org.winterframework.dashboard.base.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.base.model.data.MenuTree;
import org.winterframework.dashboard.base.service.MenuService;
import org.winterframework.dashboard.web.model.APIResponse;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/tree")
    public APIResponse<List<MenuTree>> getTree() {
        return APIResponse.success(menuService.getTree());
    }
}
