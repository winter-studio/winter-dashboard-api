package org.winterframework.dashboard.base.service;

import org.winterframework.dashboard.base.entity.Role;
import org.winterframework.dashboard.base.mapper.RoleMapper;
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
