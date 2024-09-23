package cn.wintersoft.dashboard.security.model;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@Schema(description = "登出请求")
public record UserLogoutRequest(
        @Schema(description = "refreshToken")
        String refreshToken) {
}
