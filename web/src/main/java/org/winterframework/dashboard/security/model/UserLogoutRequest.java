package org.winterframework.dashboard.security.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "登出请求")
public record UserLogoutRequest(
        @Schema(description = "refreshToken")
        String refreshToken) {
}
