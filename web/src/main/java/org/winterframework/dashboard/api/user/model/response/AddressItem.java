package org.winterframework.dashboard.api.user.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressItem {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "电话")
    private String tel;
    @Schema(description = "详细地址")
    private String address;
    @Schema(description = "是否为默认地址")
    private Boolean isDefault;
}
