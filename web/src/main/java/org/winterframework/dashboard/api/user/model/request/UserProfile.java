package org.winterframework.dashboard.api.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "用户信息")
public class UserProfile {
    private String avatar;
    @NotNull(message = "昵称不能为空")
    private String nickname;
}
