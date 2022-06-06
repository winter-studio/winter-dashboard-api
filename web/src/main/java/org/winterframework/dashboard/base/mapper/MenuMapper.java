package org.winterframework.dashboard.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.winterframework.dashboard.base.entity.Menu;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByUserId(@Param("userId") Long userId);

    Integer getMaxSort(@Param("parentId") Integer parentId);

    boolean hasChildren(Integer id);
}
