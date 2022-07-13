package org.winterframework.dashboard.api.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Tag(name = "角色模块")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

}
