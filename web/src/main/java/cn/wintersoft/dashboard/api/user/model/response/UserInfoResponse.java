package cn.wintersoft.dashboard.api.user.model.response;

import cn.wintersoft.dashboard.api.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(description = "用户信息")
@Getter
@Setter
@NoArgsConstructor
public class UserInfoResponse {
    @Schema(description = "user ID")
    private long userId;
    @Schema(description = "username")
    private String username;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "状态")
    private String status;
    @Schema(description = "角色")
    private List<String> roles;

    public UserInfoResponse(User user, List<String> roles) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.mobile = user.getMobile();
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
        this.status = user.getStatus();
        this.roles = roles;
    }
}
