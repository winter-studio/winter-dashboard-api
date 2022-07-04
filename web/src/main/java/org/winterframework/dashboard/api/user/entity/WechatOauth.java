package org.winterframework.dashboard.api.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Kyun
 * @since 2022-06-15
 */
@Getter
@Setter
@TableName("wechat_oauth")
@Schema(name = "WechatOauth", description = "")
public class WechatOauth {

    @Schema(description = "微信openId")
    private String id;

    private String accessToken;

    private LocalDateTime accessTokenExpiredAt;

    private String refreshToken;

    private LocalDateTime refreshTokenExpiredAt;


}
