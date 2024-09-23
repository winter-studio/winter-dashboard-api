package cn.wintersoft.dashboard.security.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@Schema(description = "登录请求")
public record UserLoginRequest(
        @Schema(description = "username")
        @NotBlank(message = "用户名不能为空")
        String username,
        @NotBlank(message = "密码不能为空")
        @Schema(description = "password") String password) {
}
