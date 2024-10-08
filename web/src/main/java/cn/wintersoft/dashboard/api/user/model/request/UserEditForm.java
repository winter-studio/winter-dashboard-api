package cn.wintersoft.dashboard.api.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

@Data
@Schema(description = "管理员用户列表项")
public class UserEditForm {

    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "用户名")
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "用户名必须是4-16位字母、数字、下划线")
    private String username;
    @NotNull(message = "昵称不能为空")
    @Size(max = 20, message = "昵称最大长度为20")
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "密码")
    @Size(min = 6, max = 32, message = "密码长度6-32")
    // @NotNull(message = "密码不能为空")
    private String password;
    @Schema(description = "手机号")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式不正确")
    private String mobile;
    @Schema(description = "状态")
    @NotNull(message = "状态不能为空")
    private String status;
    @Schema(description = "角色")
    private List<String> roles;

}
