package cn.wintersoft.dashboard.api.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import cn.wintersoft.dashboard.api.user.entity.User;
import cn.wintersoft.dashboard.api.user.model.request.AdminUserPageReq;
import cn.wintersoft.dashboard.api.user.model.response.AdminUserForm;
import cn.wintersoft.dashboard.api.user.model.response.AdminUserPageItem;

/**
 * @author Kyun
 * @since 2022-05-23
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<AdminUserPageItem> pagingQueryUsers(Page<?> page, @Param("query") AdminUserPageReq req);

    AdminUserForm queryUserFormById(@Param("id") Long id);
}
