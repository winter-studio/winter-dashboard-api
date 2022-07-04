package org.winterframework.dashboard.api.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.winterframework.dashboard.api.user.entity.UserRole;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("SELECT code from role where id in (SELECT role_id FROM user_role WHERE user_id = #{userId})")
    List<String> getUserRoles(Long userId);
}
