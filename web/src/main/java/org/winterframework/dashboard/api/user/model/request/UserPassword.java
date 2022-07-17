package org.winterframework.dashboard.api.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(description = "更改密码")
@Data
public class UserPassword {
    @NotNull(message = "原密码不能为空")
    private String oldPassword;
    @NotNull(message = "新密码不能为空")
    private String newPassword;
}
