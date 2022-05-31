package org.winterframework.dashboard.base.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "创建用户请求")
public record CreateUserReq(
        @Schema(description = "用户名")
        @NotNull(message = "用户名不能为空")
        String username,
        @Schema(description = "密码")
        @NotNull(message = "密码不能为空")
        String password
) {
}
