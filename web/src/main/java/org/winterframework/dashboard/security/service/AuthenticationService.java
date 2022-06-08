package org.winterframework.dashboard.security.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.winterframework.dashboard.base.entity.User;
import org.winterframework.dashboard.base.service.UserService;
import org.winterframework.dashboard.security.core.JwtProvider;
import org.winterframework.dashboard.security.model.UserLoginRequest;
import org.winterframework.dashboard.security.model.UserLoginResponse;
import org.winterframework.dashboard.web.exception.ApiFailureException;
import org.winterframework.dashboard.web.model.ApiResCodes;

import java.util.UUID;

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
        return new UserLoginResponse(userLoginRequest.username(), userLoginRequest.username(), token, refreshToken);
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
        String userId = jwtProvider.validateRefreshToken(refreshToken);
        if (userId == null) {
            throw new ApiFailureException(ApiResCodes.Failure.JWT_REFRESH_TOKEN_INVALID, "refresh token is invalid");
        }
        return createAccessToken(userId);
    }
}
