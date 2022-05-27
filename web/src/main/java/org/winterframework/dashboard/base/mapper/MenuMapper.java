package org.winterframework.dashboard.base.mapper;

import org.apache.ibatis.annotations.Param;
import org.winterframework.dashboard.base.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByUserId(@Param("userId") String userId);
}
