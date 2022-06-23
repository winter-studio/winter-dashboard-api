package org.winterframework.dashboard.api.user.service;

import org.winterframework.dashboard.api.user.entity.Role;
import org.winterframework.dashboard.api.user.mapper.RoleMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IService<Role> {

}