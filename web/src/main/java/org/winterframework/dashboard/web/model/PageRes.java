package org.winterframework.dashboard.web.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "分页结果")
@Data
public class PageRes<T> implements Serializable {
    @Schema(description = "数据")
    private List<T> list;
    @Schema(description = "总页数")
    private long total;
    @Schema(description = "当前页数")
    private long current;
    @Schema(description = "每页大小")
    private long size;
    @Schema(description = "是否还有更多数据")
    private boolean hasMore;

    public static <T> PageRes<T> of(IPage<T> mpPage) {
        PageRes<T> pageRes = new PageRes<>();
        pageRes.setCurrent(mpPage.getCurrent());
        pageRes.setSize(mpPage.getSize());
        pageRes.setTotal(mpPage.getTotal());
        pageRes.setList(mpPage.getRecords());
        pageRes.setHasMore(mpPage.getCurrent() < mpPage.getPages());
        return pageRes;
    }
}
