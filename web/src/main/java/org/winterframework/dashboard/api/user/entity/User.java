package org.winterframework.dashboard.api.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author Kyun
 * @since 2022-06-15
 */
@Getter
@Setter
@Schema(name = "User", description = "用户")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableLogic
    private Boolean deleted;

    @Schema(description = "微信openId")
    private String openId;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像url")
    private String avatar;

    @Schema(description = "电话号码")
    private String mobile;

}
