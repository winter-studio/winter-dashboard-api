package org.winterframework.dashboard.api.base.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.api.base.model.data.MenuTree;
import org.winterframework.dashboard.api.base.model.request.CreateUserReq;
import org.winterframework.dashboard.api.base.model.response.CreateUserRes;
import org.winterframework.dashboard.api.base.service.UserService;
import org.winterframework.dashboard.web.model.ApiRes;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-23
 */
@Tag(name = "用户模块")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "创建用户")
    @PostMapping
    public ApiRes<CreateUserRes> createUser(@Validated @RequestBody CreateUserReq req) {
        CreateUserRes res = userService.createUser(req);
        return ApiRes.<CreateUserRes>baseOn(res == null)
                     .successThen().data(res)
                     .failureThen().message("创建用户失败");
    }

    @Operation(summary = "获取当前登录用户菜单列表")
    @GetMapping("/me/menus")
    public ApiRes<List<MenuTree>> createUser() {
        List<MenuTree> res = userService.getCurrentUserMenuTree();
        return ApiRes.<List<MenuTree>>baseOn(res != null)
                     .successThen().data(res)
                     .failureThen().message("查询菜单失败");
    }

}
