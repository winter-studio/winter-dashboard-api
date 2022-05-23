package org.winterframework.dashboard.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.winterframework.dashboard.base.model.request.CreateUserReq;
import org.winterframework.dashboard.base.model.response.CreateUserRes;
import org.winterframework.dashboard.base.service.UserService;
import org.winterframework.dashboard.web.model.APIResponse;

/**
 * @author Kyun
 * @since 2022-05-23
 */
@Api(tags = "用户模块")
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("创建用户")
    @PostMapping
    public APIResponse<CreateUserRes> createUser(@Validated @RequestBody CreateUserReq req) {
        CreateUserRes res = userService.createUser(req);
        return res == null ? APIResponse.failure("创建用户失败") : APIResponse.success(res);
    }

}
