package org.winterframework.dashboard.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.api.user.service.RoleService;
import org.winterframework.dashboard.web.model.ApiRes;
import org.winterframework.dashboard.web.model.SelectOption;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Tag(name = "角色模块")
@RestController
@RequestMapping("/admin/roles")
@RequiredArgsConstructor
public class AdminRoleController {

    private final RoleService roleService;

    @GetMapping("/options")
    @Operation(summary = "查询角色可选项")
    public ApiRes<List<SelectOption>> getRoleOptions() {
        List<SelectOption> roles = roleService.getRoleOptions();
        return ApiRes.success(roles);
    }
}
