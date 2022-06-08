package org.winterframework.dashboard.security.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @Operation(summary = "刷新token")
    @PutMapping("/token")
    public ApiRes<String> refreshToken(@RequestHeader("x-refresh-token") String refreshToken) {

        String token = authenticateService.refreshToken(refreshToken);

        return ApiRes.success("刷新成功", token);
    }

}
