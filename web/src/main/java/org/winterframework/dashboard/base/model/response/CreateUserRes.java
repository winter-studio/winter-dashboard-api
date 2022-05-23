package org.winterframework.dashboard.base.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("创建用户响应")
public record CreateUserRes(@ApiModelProperty("用户ID") Long id,
                            @ApiModelProperty("用户名") String username) {
}
