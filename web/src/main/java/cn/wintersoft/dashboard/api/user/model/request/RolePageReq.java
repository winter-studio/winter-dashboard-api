package cn.wintersoft.dashboard.api.user.model.request;

import cn.wintersoft.dashboard.web.model.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "角色分页查询参数")
@Getter
@Setter
public class RolePageReq extends PageParam {
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "角色代码")
    private String roleCode;
}
