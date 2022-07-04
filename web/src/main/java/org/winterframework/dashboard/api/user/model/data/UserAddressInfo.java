package org.winterframework.dashboard.api.user.model.data;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "用户地址")
public record UserAddressInfo(
        @Schema(description = "姓名")
        @NotNull(message = "姓名不能为空")
        String name,

        @Schema(description = "电话")
        @NotNull(message = "电话不能为空")
        String tel,


        @Schema(description = "国家")
        String country,

        @Schema(description = "省份")
        @NotNull(message = "省份不能为空")
        String province,

        @Schema(description = "城市")
        @NotNull(message = "城市不能为空")
        String city,

        @Schema(description = "区县")
        @NotNull(message = "区县不能为空")
        String county,

        @Schema(description = "详细地址")
        @NotNull(message = "详细地址不能为空")
        String addressDetail,

        @Schema(description = "地区编码")
        @NotNull(message = "地区编码不能为空")
        String areaCode,

        @Schema(description = "邮编")
        String postalCode,

        @Schema(description = "是否为默认地址")
        Boolean isDefault
) {
}
