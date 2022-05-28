package org.winterframework.dashboard.base.model.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.winterframework.dashboard.base.entity.Menu;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"sort", "parentId"})
@ApiModel("菜单")
public class MenuTree extends Menu implements Comparable<MenuTree> {

    @ApiModelProperty("子节点")
    private List<MenuTree> children = new ArrayList<>();

    public MenuTree(Menu menu) {
        this.setId(menu.getId());
        this.setParentId(menu.getParentId());
        this.setPath(menu.getPath());
        this.setTitle(menu.getTitle());
        this.setIcon(menu.getIcon());
        this.setExtra(menu.getExtra());
        this.setData(menu.getData());
        this.setType(menu.getType());
        this.setHidden(menu.getHidden());
        this.setKeepAlive(menu.getKeepAlive());
        this.setPermitAll(menu.getPermitAll());
        this.setSort(menu.getSort());
    }

    @Override
    public int compareTo(MenuTree o) {
        return this.getSort() - o.getSort();
    }
}
