package org.winterframework.dashboard.security.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.security.model.UserLoginRequest;
import org.winterframework.dashboard.security.model.UserLoginResponse;
import org.winterframework.dashboard.security.service.AuthenticationService;
import org.winterframework.dashboard.web.model.ApiResponse;

@Api(tags = "认证模块")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticateService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ApiResponse<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {

        UserLoginResponse res = authenticateService.authenticate(userLoginRequest);

        return ApiResponse.success(res);
    }
}
