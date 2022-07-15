package org.winterframework.dashboard.api.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.winterframework.dashboard.api.user.entity.RoleMenu;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Integer> getMenuIdsByRoleId(@Param("roleId") Integer id);
}
