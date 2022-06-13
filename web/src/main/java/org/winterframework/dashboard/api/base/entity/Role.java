package org.winterframework.dashboard.api.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-05-26
 */
@Getter
@Setter
@Schema(name = "Role", description = "")
public class Role {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色代码")
    private String code;


}
