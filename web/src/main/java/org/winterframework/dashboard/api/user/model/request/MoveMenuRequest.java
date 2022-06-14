package org.winterframework.dashboard.api.user.model.request;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "移动菜单请求")
public record MoveMenuRequest(
        @Schema(description = "相对菜单id")
        @NotNull(message = "相对菜单id不能为空")
        Integer relative,
        @Schema(description = "类型")
        @NotNull(message = "类型不能为空")
        Position position
) {
        public enum Position {
                AFTER("after"),
                BEFORE("before"),
                INSIDE("inside");

                private final String position;

                Position(String position) {
                        this.position = position;
                }

                @JsonValue
                public String getValue() {
                        return position;
                }

        }
}
