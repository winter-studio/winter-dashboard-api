package org.winterframework.dashboard.base.entity;

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
@TableName("user_role")
@Schema(name = "UserRole", description = "")
public class UserRole {

    private Long userId;
    private Integer roleId;

}
