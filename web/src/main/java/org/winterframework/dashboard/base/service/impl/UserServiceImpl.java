package org.winterframework.dashboard.base.service.impl;

import org.winterframework.dashboard.base.entity.User;
import org.winterframework.dashboard.base.mapper.UserMapper;
import org.winterframework.dashboard.base.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kyun
 * @since 2022-05-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
