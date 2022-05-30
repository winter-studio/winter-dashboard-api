package org.winterframework.dashboard.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.base.model.data.MenuTree;
import org.winterframework.dashboard.base.model.request.CreateUserReq;
import org.winterframework.dashboard.base.model.response.CreateUserRes;
import org.winterframework.dashboard.base.service.UserService;
import org.winterframework.dashboard.web.model.ApiRes;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-23
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("创建用户")
    @PostMapping
    public ApiRes<CreateUserRes> createUser(@Validated @RequestBody CreateUserReq req) {
        CreateUserRes res = userService.createUser(req);
        return res == null ? ApiRes.failure("创建用户失败") : ApiRes.success(res);
    }

    @ApiOperation("获取当前登录用户菜单列表")
    @GetMapping("/me/menus")
    public ApiRes<List<MenuTree>> createUser() {
        List<MenuTree> res = userService.getCurrentUserMenuTree();
        return res == null ? ApiRes.failure("查询菜单失败") : ApiRes.success(res);
    }

}
