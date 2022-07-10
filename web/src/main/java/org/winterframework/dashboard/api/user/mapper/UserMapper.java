package org.winterframework.dashboard.api.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.winterframework.dashboard.api.user.entity.User;
import org.winterframework.dashboard.api.user.model.request.AdminUserPageReq;
import org.winterframework.dashboard.api.user.model.response.AdminUserPageItem;

/**
 * @author Kyun
 * @since 2022-05-23
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<AdminUserPageItem> pagingQueryUsers(Page<?> page, @Param("query") AdminUserPageReq req);
}
