package org.winterframework.dashboard.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Menu", description = "")
public class Menu {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("父节点")
    private Integer parentId;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("额外标识")
    private String extra;

    @ApiModelProperty("页面组件/链接")
    private String data;

    @ApiModelProperty("组件类型(dir/view/link/iframe)")
    private String type;

    @ApiModelProperty("是否隐藏")
    private Boolean hidden;

    @ApiModelProperty("是否缓存")
    private Boolean keepAlive;

    @ApiModelProperty("是否可匿名访问")
    private Boolean permitAll;

    @ApiModelProperty("排序(基于同级)")
    private Integer sort;

    @TableLogic
    private Boolean deleted;

}
