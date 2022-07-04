package org.winterframework.dashboard.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.api.user.model.data.UserAddressInfo;
import org.winterframework.dashboard.api.user.model.response.AddressItem;
import org.winterframework.dashboard.api.user.service.UserAddressService;
import org.winterframework.dashboard.web.model.ApiRes;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-06-21
 */
@Tag(name ="用户地址模块")
@RestController
@RequestMapping("/user-addresses")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    @Operation(summary = "获取地址列表")
    @GetMapping
    public ApiRes<List<AddressItem>> getUserAddresses() {
        return ApiRes.success(userAddressService.getUserAddresses());
    }

    @Operation(summary = "获取默认地址")
    @GetMapping("/default")
    public ApiRes<AddressItem> getUserDefaultAddress() {
        return ApiRes.success(userAddressService.getDefaultAddress());
    }

    @Operation(summary = "获取地址")
    @GetMapping("/{id}")
    public ApiRes<UserAddressInfo> getUserAddress(@PathVariable Long id) {
        return ApiRes.success(userAddressService.getUserAddress(id));
    }

    @Operation(summary = "保存地址")
    @PostMapping
    public ApiRes<Long> createUserAddress(@Validated @RequestBody UserAddressInfo request) {
        Long res = userAddressService.saveUserAddress(request);
        return ApiRes.<Long>baseOn(res != null)
                     .successThen().data(res)
                     .failureThen().message("保存失败");
    }

    @Operation(summary = "更新地址")
    @PutMapping("/{id}")
    public ApiRes<Void> updateUserAddress(@PathVariable Long id,
                                          @Validated @RequestBody UserAddressInfo request) {
        int success = userAddressService.updateUserAddress(id, request);
        return ApiRes.<Void>baseOn(success > 0)
                     .successThen().message("更新成功")
                     .failureThen().message("更新失败");
    }

    @Operation(summary = "删除地址")
    @DeleteMapping("/{id}")
    public ApiRes<Void> deleteUserAddress(@PathVariable Long id) {
        boolean success = userAddressService.delete(id);
        return ApiRes.<Void>baseOn(success)
                     .successThen().message("删除成功")
                     .failureThen().message("删除失败");
    }
}
