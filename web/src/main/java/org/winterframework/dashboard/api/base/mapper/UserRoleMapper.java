package org.winterframework.dashboard.api.base.mapper;

import org.winterframework.dashboard.api.base.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<String> getUserRoles(String userId);
}
