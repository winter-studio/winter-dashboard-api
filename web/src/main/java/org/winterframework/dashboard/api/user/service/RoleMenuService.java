package org.winterframework.dashboard.api.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.winterframework.dashboard.api.user.entity.RoleMenu;
import org.winterframework.dashboard.api.user.mapper.RoleMenuMapper;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IService<RoleMenu> {

}
