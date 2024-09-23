package cn.wintersoft.dashboard.api.dict.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * <p>
 *
 * </p>
 *
 * @author Kyun
 * @since 2022-07-22
 */
@Getter
@Setter
@TableName("dict_item")
@Schema(name = "DictItem", description = "")
public class DictItem {

    @Schema(description = "dict.code")
    @JsonIgnore
    private String dictCode;

    @Schema(description = "key")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "字典项代码只能包含字母、数字和下划线")
    @Size(max = 100, message = "字典项代码长度不能超过100")
    @TableField("`key`")
    private String key;

    @Schema(description = "value")
    @Size(max = 200, message = "字典项值长度不能超过200")
    private String value;

    @Schema(description = "extra")
    @Size(max = 200, message = "字典项附加值长度不能超过200")
    private String extra;


}
