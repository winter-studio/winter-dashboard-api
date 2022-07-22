package cn.wintersoft.dashboard.api.dict.model;

import cn.wintersoft.dashboard.api.dict.entity.DictItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "字典")
public class DictModel {
    @Schema(description = "字典代码")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "字典代码只能包含字母、数字和下划线")
    @Size(max = 100, message = "字典代码长度不能超过100")
    private String code;
    @Schema(description = "字典名称")
    @Size(max = 100, message = "字典名称长度不能超过100")
    private String name;
    @Schema(description = "字典项")
    private List<DictItem> items;
}
