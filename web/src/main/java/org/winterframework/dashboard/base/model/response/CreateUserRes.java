package org.winterframework.dashboard.base.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;

@Schema(description = "创建用户响应")
public record CreateUserRes(@Schema(description = "用户ID") Long id,
                            @Schema(description = "用户名") String username) {
}
