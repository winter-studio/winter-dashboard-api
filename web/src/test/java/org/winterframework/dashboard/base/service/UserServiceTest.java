package org.winterframework.dashboard.base.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.winterframework.dashboard.base.model.request.CreateUserReq;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void createUser() {
        userService.createUser(new CreateUserReq("admin", "123456"));
    }
}
