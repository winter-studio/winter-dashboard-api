package org.winterframework.dashboard.base.service;

import org.winterframework.dashboard.base.entity.User;
import org.winterframework.dashboard.base.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Kyun
 * @since 2022-05-23
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User> {

}
