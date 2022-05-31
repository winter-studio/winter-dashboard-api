package org.winterframework.dashboard.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

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
    private Integer parentId;

    @Schema(description = "路径")
    private String path;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "额外标识")
    private String extra;

    @Schema(description = "页面组件/链接")
    private String data;

    @Schema(description = "组件类型(dir/view/link/iframe)")
    private String type;

    @Schema(description = "是否隐藏")
    private Boolean hidden;

    @Schema(description = "是否缓存")
    private Boolean keepAlive;

    @Schema(description = "是否可匿名访问")
    private Boolean permitAll;

    @Schema(description = "排序(基于同级)")
    private Integer sort;

    @TableLogic
    private Boolean deleted;

}
