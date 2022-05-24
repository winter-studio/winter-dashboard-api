package org.winterframework.dashboard.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel("登录请求")
public record UserLoginRequest(
        @ApiModelProperty("username")
        @NotBlank(message = "用户名不能为空")
        String username,
        @NotBlank(message = "密码不能为空")
        @ApiModelProperty("password") String password) {
}
