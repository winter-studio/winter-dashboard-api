package org.winterframework.dashboard.api.base.service;

import org.winterframework.dashboard.api.base.entity.RoleMenu;
import org.winterframework.dashboard.api.base.mapper.RoleMenuMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IService<RoleMenu> {

}
