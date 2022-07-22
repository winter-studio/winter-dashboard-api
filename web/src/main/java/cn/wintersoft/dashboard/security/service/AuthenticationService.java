package cn.wintersoft.dashboard.security.service;


import cn.wintersoft.dashboard.api.user.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cn.wintersoft.dashboard.api.user.entity.User;
import cn.wintersoft.dashboard.api.user.model.response.UserInfoResponse;
import cn.wintersoft.dashboard.api.user.service.UserRoleService;
import cn.wintersoft.dashboard.security.core.JwtProvider;
import cn.wintersoft.dashboard.security.model.request.UserLoginRequest;
import cn.wintersoft.dashboard.security.model.request.UserLogoutRequest;
import cn.wintersoft.dashboard.security.model.response.UserLoginResponse;
import cn.wintersoft.dashboard.security.utils.SecurityUtils;
import cn.wintersoft.dashboard.web.exception.ApiFailureException;
import cn.wintersoft.dashboard.web.model.ApiResCodes;

import java.util.List;

import static cn.wintersoft.dashboard.security.core.JwtProvider.TOKEN_TYPE_ACCESS;
import static cn.wintersoft.dashboard.security.core.JwtProvider.TOKEN_TYPE_REFRESH;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final UserRoleService userRoleService;

    public UserLoginResponse authenticate(UserLoginRequest userLoginRequest) {
        User user = userService.getByUsername(userLoginRequest.username());
        if (user == null) {
            throw new ApiFailureException("用户不存在");
        }

        if (!passwordEncoder.matches(userLoginRequest.password(), user.getPassword())) {
            throw new ApiFailureException("密码错误");
        }

        return createUserLoginResponse(user);
    }

    private UserLoginResponse createUserLoginResponse(User user) {
        List<String> userRoles = userRoleService.getUserRoles(user.getId());
        String token = createAccessToken(user.getId(), userRoles);
        // refresh token
        String refreshToken = createRefreshToken(user.getId().toString());
        return new UserLoginResponse(new UserInfoResponse(user, userRoles), token, refreshToken,
                jwtProvider.getRefreshTokenExpireInSeconds());
    }

    private String createRefreshToken(String userId) {
        return jwtProvider.createRefreshToken(userId);
    }

    private String createAccessToken(Long userId, List<String> userRoles) {
        return jwtProvider.createToken(userId, userRoles);
    }

    public String refreshToken(String refreshToken) {
        log.info("refreshToken: {}", refreshToken);
        String userIdStr = jwtProvider.validateRefreshToken(refreshToken);
        if (userIdStr == null) {
            throw new ApiFailureException(ApiResCodes.Failure.JWT_REFRESH_TOKEN_INVALID, "refresh token is invalid");
        }
        Long userId = Long.valueOf(userIdStr);
        List<String> userRoles = userRoleService.getUserRoles(userId);
        return createAccessToken(userId, userRoles);
    }

    public void revokeToken(UserLogoutRequest userLoginRequest) {
        if (userLoginRequest.refreshToken() != null) {
            // revoke refresh token
            Claims claims = jwtProvider.getClaims(userLoginRequest.refreshToken());
            jwtProvider.revokeToken(TOKEN_TYPE_REFRESH, claims);
        }
        if (SecurityUtils.isAuthenticated()) {
            // revoke access token
            Claims claims = SecurityUtils.getClimes();
            jwtProvider.revokeToken(TOKEN_TYPE_ACCESS, claims);
        }
    }


}
