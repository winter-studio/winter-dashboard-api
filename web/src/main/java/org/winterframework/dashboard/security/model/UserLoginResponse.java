package org.winterframework.dashboard.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public record UserLoginResponse(
        @ApiModelProperty("user ID") String userId,
        @ApiModelProperty("username") String username,
        @ApiModelProperty("accessToken") String accessToken,
        @ApiModelProperty("refreshToken") String refreshToken
) {
}
