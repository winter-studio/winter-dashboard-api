package cn.wintersoft.dashboard.api.dict.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

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
@Schema(name = "Dict", description = "")
public class Dict {

    @TableId
    @Schema(description = "字典代码")
    private String code;

    @Schema(description = "字典名称")
    private String name;

}
