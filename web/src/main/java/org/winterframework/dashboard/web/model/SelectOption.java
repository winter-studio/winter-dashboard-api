package org.winterframework.dashboard.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "选项，通常是一个下拉选项")
@Getter
@Setter
public class SelectOption {
    @Schema(description = "值")
    private String value;
    @Schema(description = "文本")
    private String label;
}
