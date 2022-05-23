package org.winterframework.dashboard.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.winterframework.dashboard.base.entity.User;
import org.winterframework.dashboard.base.mapper.UserMapper;
import org.winterframework.dashboard.base.model.request.CreateUserReq;
import org.winterframework.dashboard.base.model.response.CreateUserRes;

/**
 * @author Kyun
 * @since 2022-05-23
 */
@Service
@RequiredArgsConstructor
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {

    private final PasswordEncoder passwordEncoder;

    public CreateUserRes createUser(CreateUserReq req) {
        User user = new User();
        user.setUsername(req.username());
        user.setPassword(passwordEncoder.encode(req.password()));
        boolean saved = this.save(user);
        return saved ? new CreateUserRes(user.getId(), user.getUsername()) : null;
    }
}
