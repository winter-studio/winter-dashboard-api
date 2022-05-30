package org.winterframework.dashboard.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("user_role")
@ApiModel(value = "UserRole", description = "")
public class UserRole {

    @TableId
    private Long userId;
    @TableId
    private Integer roleId;


}
