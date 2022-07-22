package cn.wintersoft.dashboard.api.dict.service;

import cn.wintersoft.dashboard.api.dict.entity.DictItem;
import cn.wintersoft.dashboard.api.dict.mapper.DictItemMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-07-22
 */
@Service
public class DictItemService extends ServiceImpl<DictItemMapper, DictItem> implements IService<DictItem> {

    public List<DictItem> getDictItems(String code) {
        return this.list(Wrappers.lambdaQuery(DictItem.class).eq(DictItem::getDictCode, code));
    }

    public void deleteDictItems(List<String> codes) {
        this.remove(Wrappers.lambdaQuery(DictItem.class).in(DictItem::getDictCode, codes));
    }

    public void saveDictItems(String code, List<DictItem> items) {
        this.remove(Wrappers.lambdaQuery(DictItem.class).eq(DictItem::getDictCode, code));
        items.forEach(item -> item.setDictCode(code));
        this.saveBatch(items);
    }
}
