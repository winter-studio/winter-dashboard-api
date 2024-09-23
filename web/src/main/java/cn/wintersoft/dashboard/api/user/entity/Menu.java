package cn.wintersoft.dashboard.api.user.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author Kyun
 * @since 2022-05-28
 */
@Getter
@Setter
@Schema(name = "Menu", description = "")
public class Menu {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "父节点")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer parentId;

    @Schema(description = "路径")
    @NotEmpty(message = "路径不能为空")
    private String path;

    @Schema(description = "标题")
    @NotEmpty(message = "标题不能为空")
    private String title;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "额外标识")
    private String tags;

    @Schema(description = "页面组件/链接")
    private String data;

    @Schema(description = "类型(1:dir/2:view/3:link/4:iframe)")
    @NotNull(message = "类型不能为空")
    private Short type;

    @Schema(description = "是否隐藏")
    private Boolean hidden;

    @Schema(description = "是否缓存")
    private Boolean keepAlive;

    @Schema(description = "排序(基于同级)")
    private Integer sort;

}
