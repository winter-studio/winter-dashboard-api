package org.winterframework.dashboard.base.service;

import org.winterframework.dashboard.base.entity.Menu;
import org.winterframework.dashboard.base.mapper.MenuMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IService<Menu> {

}
