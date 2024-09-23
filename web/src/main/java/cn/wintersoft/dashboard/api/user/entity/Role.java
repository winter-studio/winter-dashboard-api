package cn.wintersoft.dashboard.api.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author Kyun
 * @since 2022-05-26
 */
@Getter
@Setter
@Schema(name = "Role", description = "")
public class Role {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "角色名称")
    @NotNull
    private String name;

    @Schema(description = "角色代码")
    @NotNull
    private String code;

}
