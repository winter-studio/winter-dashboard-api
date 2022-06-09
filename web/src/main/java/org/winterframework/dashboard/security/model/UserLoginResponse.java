package org.winterframework.dashboard.security.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record UserLoginResponse(
        @Schema(description = "user ID") String userId,
        @Schema(description = "username") String username,
        @Schema(description = "accessToken") String accessToken,
        @Schema(description = "refreshToken") String refreshToken,
        @Schema(description = "refreshTokenExpireIn") int refreshTokenExpireIn
        ) {
}
