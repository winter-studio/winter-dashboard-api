package org.winterframework.dashboard.security.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.winterframework.dashboard.security.core.JwtProvider;
import org.winterframework.dashboard.security.model.UserLoginRequest;
import org.winterframework.dashboard.security.model.UserLoginResponse;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtProvider jwtProvider;

    public UserLoginResponse authenticate(UserLoginRequest userLoginRequest) {
        String token = jwtProvider.createToken(userLoginRequest.username(), Collections.singletonList("ADMIN"));
        return new UserLoginResponse(userLoginRequest.username(), userLoginRequest.username(), token, null);
    }
}
