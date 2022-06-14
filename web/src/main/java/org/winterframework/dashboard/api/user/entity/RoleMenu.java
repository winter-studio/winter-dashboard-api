package org.winterframework.dashboard.api.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("role_menu")
@Schema(name = "RoleMenu", description = "")
public class RoleMenu {

    private Integer roleId;
    private Integer menuId;

}
