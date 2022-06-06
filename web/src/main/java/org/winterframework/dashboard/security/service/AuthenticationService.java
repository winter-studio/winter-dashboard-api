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

import java.util.Collections;

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

        String token = jwtProvider.createToken(user.getId().toString(), Collections.singletonList("ADMIN"));
        return new UserLoginResponse(userLoginRequest.username(), userLoginRequest.username(), token, null);
    }
}
