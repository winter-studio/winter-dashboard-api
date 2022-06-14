package org.winterframework.dashboard.api.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.winterframework.dashboard.api.user.entity.Menu;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-05-26
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByUserId(@Param("userId") Long userId);

    Integer getMaxSortInDirectory(@Param("parentId") Integer parentId);

    int moveEachMenuDownAfterRelative(@Param("parentId") Integer parentId, @Param("sort") Integer sort,
                                      @Param("moveWithRelativeMenu") boolean moveWithRelativeMenu);
}
