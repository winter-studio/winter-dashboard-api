package cn.wintersoft.dashboard.api.user.controller;

import cn.wintersoft.dashboard.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.wintersoft.dashboard.api.user.model.request.AdminUserPageReq;
import cn.wintersoft.dashboard.api.user.model.request.UserEditForm;
import cn.wintersoft.dashboard.api.user.model.response.AdminUserForm;
import cn.wintersoft.dashboard.api.user.model.response.AdminUserPageItem;
import cn.wintersoft.dashboard.web.model.ApiRes;
import cn.wintersoft.dashboard.web.model.PageRes;

/**
 * @author Kyun
 * @since 2022-05-23
 */
@Tag(name = "用户模块")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole(@Roles.ADMIN)")
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

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public ApiRes<Void> deleteUser(@PathVariable("id") Long id) {
        userService.removeById(id);
        return ApiRes.success();
    }

    @Operation(summary = "更改用户状态")
    @PutMapping("/{id}/status")
    public ApiRes<Void> deleteUser(@PathVariable("id") Long id, @RequestParam("status") String status) {
        userService.changeUserStatus(id, status);
        return ApiRes.success();
    }

    @Operation(summary = "新增用户")
    @PostMapping
    public ApiRes<Long> addUser(@Validated @RequestBody UserEditForm form) {
        return ApiRes.success(userService.addUser(form));
    }

    @Operation(summary = "新增用户")
    @PutMapping("/{id}")
    public ApiRes<Void> addUser(@PathVariable("id") Long id, @Validated @RequestBody UserEditForm form) {
        userService.editUser(id, form);
        return ApiRes.success();
    }

}

