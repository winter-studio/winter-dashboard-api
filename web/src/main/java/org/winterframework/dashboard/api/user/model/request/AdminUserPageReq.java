package org.winterframework.dashboard.api.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.winterframework.dashboard.web.model.PageParam;

@Schema(description = "管理员用户分页查询参数")
@Getter
@Setter
public class AdminUserPageReq extends PageParam {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "手机号")
    private String mobile;
}
