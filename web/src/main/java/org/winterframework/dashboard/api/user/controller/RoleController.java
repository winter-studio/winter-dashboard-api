package org.winterframework.dashboard.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.dashboard.api.user.entity.Role;
import org.winterframework.dashboard.api.user.service.RoleMenuService;
import org.winterframework.dashboard.api.user.service.RoleService;
import org.winterframework.dashboard.web.model.ApiRes;
import org.winterframework.dashboard.web.model.SelectOption;
import org.winterframework.dashboard.web.resolver.JsonParam;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Tag(name = "角色模块")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@PreAuthorize("hasRole(@Roles.ADMIN)")
public class RoleController {
    private final RoleService roleService;
    private final RoleMenuService roleMenuService;

    @GetMapping("/options")
    @Operation(summary = "查询角色可选项")
    public ApiRes<List<SelectOption>> getRoleOptions() {
        List<SelectOption> roles = roleService.getRoleOptions();
        return ApiRes.success(roles);
    }

    @GetMapping
    @Operation(summary = "查询所有角色")
    public ApiRes<List<Role>> getRoles() {
        List<Role> roles = roleService.list();
        return ApiRes.success(roles);
    }

    @Operation(summary = "获取角色")
    @GetMapping("/{id}")
    public ApiRes<Role> getRoleById(@PathVariable Integer id) {
        Role role = roleService.getById(id);
        return ApiRes.success(role);
    }

    @Operation(summary = "新增角色")
    @PostMapping
    public ApiRes<Integer> getRoleById(@RequestBody Role role) {
        role.setId(null);
        roleService.save(role);
        return ApiRes.success(role.getId());
    }

    @Operation(summary = "编辑角色")
    @PutMapping("/{id}")
    public ApiRes<Void> getRoleById(@PathVariable Integer id, @RequestBody Role role) {
        role.setId(id);
        roleService.updateById(role);
        return ApiRes.success();
    }

    @Operation(summary = "删除角色")
    @DeleteMapping
    public ApiRes<Void> deleteRoles(@JsonParam("ids") List<Integer> ids) {
        roleService.deleteRoles(ids);
        return ApiRes.success();
    }

    @Operation(summary = "获取角色菜单")
    @GetMapping("/{id}/menus")
    public ApiRes<List<Integer>> deleteRoles(@PathVariable Integer id) {
        return ApiRes.success(roleMenuService.getRoleMenuIds(id));
    }
}
