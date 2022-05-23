package org.winterframework.dashboard.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录请求")
public record UserLoginRequest(@ApiModelProperty("username") String username,
                               @ApiModelProperty("password") String password){}
