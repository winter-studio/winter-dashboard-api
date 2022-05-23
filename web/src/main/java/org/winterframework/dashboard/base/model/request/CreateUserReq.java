package org.winterframework.dashboard.base.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel("创建用户请求")
public record CreateUserReq(
        @ApiModelProperty("用户名")
        @NotNull(message = "用户名不能为空")
        String username,
        @ApiModelProperty("密码")
        @NotNull(message = "密码不能为空")
        String password
) {
}
