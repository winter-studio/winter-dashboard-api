package org.winterframework.dashboard.api.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author Kyun
 * @since 2022-06-21
 */
@Getter
@Setter
@TableName("user_address")
@Schema(name = "UserAddress", description = "")
public class UserAddress {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "手机号")
    private String tel;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "区县")
    private String county;

    @Schema(description = "详细地址")
    private String detail;

    @Schema(description = "地区编码")
    private String areaCode;

    @Schema(description = "邮编")
    private String postalCode;

    @Schema(description = "是否为默认地址")
    private Boolean isDefault;


}
