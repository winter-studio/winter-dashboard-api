package org.winterframework.dashboard.security.service;


import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.winterframework.dashboard.base.entity.User;
import org.winterframework.dashboard.base.service.UserService;
import org.winterframework.dashboard.security.core.JwtProvider;
import org.winterframework.dashboard.security.model.UserLoginRequest;
import org.winterframework.dashboard.security.model.UserLoginResponse;
import org.winterframework.dashboard.security.model.UserLogoutRequest;
import org.winterframework.dashboard.security.utils.SecurityUtils;
import org.winterframework.dashboard.web.exception.ApiFailureException;
import org.winterframework.dashboard.web.model.ApiResCodes;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.winterframework.dashboard.security.core.JwtProvider.TOKEN_TYPE_ACCESS;
import static org.winterframework.dashboard.security.core.JwtProvider.TOKEN_TYPE_REFRESH;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    public UserLoginResponse authenticate(UserLoginRequest userLoginRequest) {
        User user = userService.getByUsername(userLoginRequest.username());
        if (user == null) {
            throw new ApiFailureException("用户不存在");
        }

        if (!passwordEncoder.matches(userLoginRequest.password(), user.getPassword())) {
            throw new ApiFailureException("密码错误");
        }

        String token = createAccessToken(user.getId().toString());
        // refresh token
        String refreshToken = createRefreshToken(user.getId().toString());
        return new UserLoginResponse(userLoginRequest.username(), userLoginRequest.username(), token, refreshToken,
                jwtProvider.getRefreshTokenExpireInSeconds());
    }

    private String createRefreshToken(String userId) {
        String refreshTokenId = UUID.randomUUID().toString().replace("-", "");
        return jwtProvider.createRefreshToken(refreshTokenId, userId);
    }

    private String createAccessToken(String userId) {
        String tokenId = UUID.randomUUID().toString().replace("-", "");
        return jwtProvider.createToken(tokenId, userId, userService.getUserRoles(userId));
    }

    public String refreshToken(String refreshToken) {
        log.info("refreshToken: {}", refreshToken);
        String userId = jwtProvider.validateRefreshToken(refreshToken);
        if (userId == null) {
            throw new ApiFailureException(ApiResCodes.Failure.JWT_REFRESH_TOKEN_INVALID, "refresh token is invalid");
        }
        return createAccessToken(userId);
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
