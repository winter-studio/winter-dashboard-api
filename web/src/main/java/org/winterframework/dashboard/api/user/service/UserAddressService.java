package org.winterframework.dashboard.api.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.winterframework.dashboard.api.user.entity.UserAddress;
import org.winterframework.dashboard.api.user.mapper.UserAddressMapper;
import org.winterframework.dashboard.api.user.model.data.UserAddressInfo;
import org.winterframework.dashboard.api.user.model.response.AddressItem;
import org.winterframework.dashboard.security.utils.SecurityUtils;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-06-21
 */
@Service
public class UserAddressService extends ServiceImpl<UserAddressMapper, UserAddress> implements IService<UserAddress> {

    @Transactional
    public Long saveUserAddress(UserAddressInfo request) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(SecurityUtils.getUserId());
        copyFieldValues(request, userAddress);
        baseMapper.insert(userAddress);
        setFirstAddressAsDefaultIfNonDefault();
        return userAddress.getId();

    }

    @Transactional
    public int updateUserAddress(Long id, UserAddressInfo request) {
        UserAddress entity = this.getById(id);
        if (entity == null) {
            throw new RuntimeException("该地址已被删除");
        }
        copyFieldValues(request, entity);
        return baseMapper.updateById(entity);
    }


    private void copyFieldValues(UserAddressInfo request, UserAddress entity) {
        entity.setName(request.name());
        entity.setTel(request.tel());
        entity.setProvince(request.province());
        entity.setCity(request.city());
        entity.setCounty(request.county());
        entity.setDetail(request.addressDetail());
        entity.setAreaCode(request.areaCode());
        entity.setIsDefault(request.isDefault());

        if (request.isDefault()) {
            // 把其他默认地址设置为非默认地址
            this.baseMapper.unsetAllDefaultAddresses(SecurityUtils.getUserId());
        }
    }

    public List<AddressItem> getUserAddresses() {
        return baseMapper.getUserAddresses(SecurityUtils.getUserId());
    }

    public UserAddressInfo getUserAddress(Long id) {
        UserAddress entity = this.getById(id);
        if (entity == null) {
            throw new RuntimeException("该地址已被删除");
        }
        return new UserAddressInfo(
                entity.getName(),
                entity.getTel(),
                entity.getCountry(),
                entity.getProvince(),
                entity.getCity(),
                entity.getCounty(),
                entity.getDetail(),
                entity.getAreaCode(),
                entity.getPostalCode(),
                entity.getIsDefault()
        );
    }

    @Transactional
    public boolean delete(Long id) {
        boolean success = this.removeById(id);
        setFirstAddressAsDefaultIfNonDefault();
        return success;
    }

    private void setFirstAddressAsDefaultIfNonDefault() {
        if (getDefaultAddress() == null) {
            baseMapper.setFirstAddressAsDefault(SecurityUtils.getUserId());
        }
    }

    public AddressItem getDefaultAddress() {
        return baseMapper.getDefaultAddress(SecurityUtils.getUserId());
    }
}
