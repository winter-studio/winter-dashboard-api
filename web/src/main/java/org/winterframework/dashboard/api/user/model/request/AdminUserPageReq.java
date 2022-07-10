package org.winterframework.dashboard.api.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.winterframework.dashboard.web.model.PageParam;

@Schema(description = "管理员用户分页查询参数")
public class AdminUserPageReq extends PageParam {
}