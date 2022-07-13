package org.winterframework.dashboard.api.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.winterframework.dashboard.api.user.entity.Role;
import org.winterframework.dashboard.web.model.SelectOption;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT id AS value, name AS label FROM role")
    List<SelectOption> queryRoleOptions();
}
