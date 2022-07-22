package cn.wintersoft.dashboard.api.user.controller;

import cn.wintersoft.dashboard.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cn.wintersoft.dashboard.api.user.model.data.MenuTree;
import cn.wintersoft.dashboard.api.user.model.request.UserPassword;
import cn.wintersoft.dashboard.api.user.model.request.UserProfile;
import cn.wintersoft.dashboard.api.user.model.response.UserInfoResponse;
import cn.wintersoft.dashboard.web.model.ApiRes;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-23
 */
@Tag(name = "用户模块")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/me")
    public ApiRes<UserInfoResponse> getMyInfo() {
        UserInfoResponse res = userService.getCurrentUserInfo();
        return ApiRes.<UserInfoResponse>baseOn(res != null)
                     .successThen().data(res)
                     .failureThen().message("获取用户信息失败");
    }

    @Operation(summary = "获取当前登录用户信息")
    @PutMapping("/me")
    public ApiRes<UserInfoResponse> updateMyInfo(@Validated @RequestBody UserProfile profile) {
        userService.updateUserProfile(profile);
        return ApiRes.success();
    }

    @Operation(summary = "更改密码")
    @PostMapping("/me/password")
    public ApiRes<UserInfoResponse> updateMyPassword(@Validated @RequestBody UserPassword password) {
        userService.updateUserPassword(password);
        return ApiRes.success();
    }

    @Operation(summary = "获取当前登录用户菜单列表")
    @GetMapping("/me/menus")
    public ApiRes<List<MenuTree>> createUser() {
        List<MenuTree> res = userService.getCurrentUserMenuTree();
        return ApiRes.<List<MenuTree>>baseOn(res != null)
                     .successThen().data(res)
                     .failureThen().message("查询菜单失败");
    }

    @Operation(summary = "上传头像", description = "上传头像，返回访问路径")
    @PostMapping(path = "/profile/avatar", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiRes<String> uploadUserAvatar(@RequestParam("avatar") MultipartFile file) throws Exception {
        String url = userService.uploadUserAvatar(file);
        return ApiRes.success(url);
    }

}

