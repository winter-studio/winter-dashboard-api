package org.winterframework.dashboard.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.api.user.model.request.AdminUserPageReq;
import org.winterframework.dashboard.api.user.model.response.AdminUserForm;
import org.winterframework.dashboard.api.user.model.response.AdminUserPageItem;
import org.winterframework.dashboard.api.user.service.UserService;
import org.winterframework.dashboard.web.model.ApiRes;
import org.winterframework.dashboard.web.model.PageRes;

/**
 * @author Kyun
 * @since 2022-05-23
 */
@Tag(name = "用户模块")
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;


    @Operation(summary = "分页查询用户列表")
    @GetMapping
    public ApiRes<PageRes<AdminUserPageItem>> getPagingUsers(AdminUserPageReq req) {
        PageRes<AdminUserPageItem> page = userService.getUserPagingList(req);
        return ApiRes.success(page);
    }

    @Operation(summary = "获取用户")
    @GetMapping("/{id}")
    public ApiRes<AdminUserForm> getUserForm(@PathVariable("id") Long id) {
        AdminUserForm form = userService.getUserForm(id);
        return ApiRes.success(form);
    }


}

