package org.winterframework.dashboard.base.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.winterframework.dashboard.base.model.request.CreateUserReq;
import org.winterframework.dashboard.base.model.response.CreateUserRes;

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
}
