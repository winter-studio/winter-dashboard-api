package org.winterframework.dashboard.base.service;

import org.winterframework.dashboard.base.entity.UserRole;
import org.winterframework.dashboard.base.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> implements IService<UserRole> {

}
