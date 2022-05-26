package org.winterframework.dashboard.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-05-26
 */
@Getter
@Setter
@TableName("role_menu")
@ApiModel(value = "RoleMenu", description = "")
public class RoleMenu {

    private Integer roleId;

    private Integer menuId;


}
