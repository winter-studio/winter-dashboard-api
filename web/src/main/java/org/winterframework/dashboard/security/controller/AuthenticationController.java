package org.winterframework.dashboard.security.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.security.model.UserLoginRequest;
import org.winterframework.dashboard.security.model.UserLoginResponse;
import org.winterframework.dashboard.security.service.AuthenticationService;
import org.winterframework.dashboard.web.model.ApiRes;

@Tag(name = "认证模块")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticateService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public ApiRes<UserLoginResponse> login(@Validated @RequestBody UserLoginRequest userLoginRequest) {

        UserLoginResponse res = authenticateService.authenticate(userLoginRequest);

        return ApiRes.success(res);
    }
}
