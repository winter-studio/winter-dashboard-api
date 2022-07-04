package org.winterframework.dashboard.security.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.winterframework.dashboard.api.user.model.response.UserInfoResponse;

@Schema
public record UserLoginResponse(
        @Schema(description = "user") UserInfoResponse user,
        @Schema(description = "accessToken") String accessToken,
        @Schema(description = "refreshToken") String refreshToken,
        @Schema(description = "refreshTokenExpireIn") int refreshTokenExpireIn
) {
}
