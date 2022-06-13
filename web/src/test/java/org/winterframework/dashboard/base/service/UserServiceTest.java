package org.winterframework.dashboard.base.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.winterframework.dashboard.api.base.model.data.MenuTree;
import org.winterframework.dashboard.api.base.model.request.CreateUserReq;
import org.winterframework.dashboard.api.base.model.response.CreateUserRes;
import org.winterframework.dashboard.api.base.service.UserService;
import org.winterframework.dashboard.security.core.JwtAuthenticationToken;
import org.winterframework.dashboard.web.utils.Jackson;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void testCreateUser() {
        CreateUserRes admin = userService.createUser(new CreateUserReq("admin", "123456"));
        assertNotNull(admin);
    }

    @Test
    void testGetCurrentUserMenuTree() throws JsonProcessingException {
        Claims claims = new DefaultClaims();
        claims.setSubject("1");
        claims.put("userId", 1);
        claims.put("username", "admin");
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(claims));
        List<MenuTree> res = userService.getCurrentUserMenuTree();
        System.out.println(Jackson.get().writeValueAsString(res));
        assertNotNull(res);
    }
}
