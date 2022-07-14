package org.winterframework.dashboard.security.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.winterframework.dashboard.api.user.model.response.UserInfoResponse;

@Getter
@Setter
@Schema(name = "用户登录响应")
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
    @Schema(description = "user")
    private UserInfoResponse user;

    @Schema(description = "accessToken")
    private String accessToken;

    @Schema(description = "refreshToken")
    private String refreshToken;

    @Schema(description = "refreshTokenExpireIn")
    private int refreshTokenExpireIn;

}


