package org.winterframework.dashboard.api.user.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "管理员用户列表项")
public class AdminUserForm {

    @Schema(description = "用户id")
    private Long id;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;
    @Schema(description = "状态")
    private String status;
    @Schema(description = "角色")
    private List<String> roles;

}
