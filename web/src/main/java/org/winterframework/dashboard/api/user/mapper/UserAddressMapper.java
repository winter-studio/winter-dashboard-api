package org.winterframework.dashboard.api.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.winterframework.dashboard.api.user.entity.UserAddress;
import org.winterframework.dashboard.api.user.model.response.AddressItem;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-06-21
 */
public interface UserAddressMapper extends BaseMapper<UserAddress> {

    void unsetAllDefaultAddresses(@Param("userId") Long userId);

    List<AddressItem> getUserAddresses(@Param("userId") Long userId);

    AddressItem getDefaultAddress(@Param("userId") Long userId);

    int setFirstAddressAsDefault(@Param("userId") Long userId);
}
