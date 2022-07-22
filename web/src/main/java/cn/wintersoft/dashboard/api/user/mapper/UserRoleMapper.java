package cn.wintersoft.dashboard.api.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import cn.wintersoft.dashboard.api.user.entity.UserRole;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("SELECT code FROM role WHERE id IN (SELECT role_id FROM user_role WHERE user_id = #{userId})")
    List<String> getUserRoles(@Param("userId") Long userId);

    @Delete("DELETE FROM user_role WHERE user_id = #{userId}")
    void removeUserRoles(@Param("userId") Long userId);
}
