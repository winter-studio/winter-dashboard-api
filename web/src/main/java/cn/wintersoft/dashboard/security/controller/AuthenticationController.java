package cn.wintersoft.dashboard.security.controller;


import cn.wintersoft.dashboard.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.wintersoft.dashboard.security.model.request.UserLoginRequest;
import cn.wintersoft.dashboard.security.model.request.UserLogoutRequest;
import cn.wintersoft.dashboard.security.model.response.UserLoginResponse;
import cn.wintersoft.dashboard.web.model.ApiRes;

@Tag(name = "认证模块")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticateService;


    @Operation(summary = "登录")
    @PostMapping("/token")
    public ApiRes<UserLoginResponse> login(@Validated @RequestBody UserLoginRequest userLoginRequest) {

        UserLoginResponse res = authenticateService.authenticate(userLoginRequest);

        return ApiRes.success(res);
    }

    @Operation(summary = "登出")
    @DeleteMapping("/token")
    public ApiRes<Void> logout(@Validated @RequestBody UserLogoutRequest userLoginRequest) {

        authenticateService.revokeToken(userLoginRequest);

        return ApiRes.success();
    }

    @Operation(summary = "刷新token")
    @PutMapping("/token")
    public ApiRes<String> refreshToken(@RequestHeader("x-refresh-token") String refreshToken) {

        String token = authenticateService.refreshToken(refreshToken);

        return ApiRes.success("刷新成功", token);
    }

}
