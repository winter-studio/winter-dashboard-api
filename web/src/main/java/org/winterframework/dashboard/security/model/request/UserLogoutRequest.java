package org.winterframework.dashboard.security.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "登出请求")
public record UserLogoutRequest(
        @Schema(description = "refreshToken")
        String refreshToken) {
}
