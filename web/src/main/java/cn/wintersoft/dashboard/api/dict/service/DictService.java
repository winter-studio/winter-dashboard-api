package cn.wintersoft.dashboard.api.dict.service;

import cn.wintersoft.dashboard.api.dict.entity.Dict;
import cn.wintersoft.dashboard.api.dict.entity.DictItem;
import cn.wintersoft.dashboard.api.dict.mapper.DictMapper;
import cn.wintersoft.dashboard.api.dict.model.DictModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-07-22
 */
@Service
@RequiredArgsConstructor
public class DictService extends ServiceImpl<DictMapper, Dict> implements IService<Dict> {

    private final DictItemService dictItemService;

    @Transactional
    public void deleteDicts(List<String> codes) {
        this.removeByIds(codes);
        dictItemService.deleteDictItems(codes);

    }

    public DictModel getDict(String code) {
        Dict dict = this.getById(code);
        if (dict == null) {
            return null;
        }
        List<DictItem> dictItems = dictItemService.getDictItems(code);
        return new DictModel(dict.getCode(), dict.getName(), dictItems);
    }

    @Transactional
    public void saveDict(DictModel dictModel) {
        Dict dict = new Dict();
        dict.setCode(dictModel.getCode());
        dict.setName(dictModel.getName());
        this.saveOrUpdate(dict);
        dictItemService.saveDictItems(dictModel.getCode(), dictModel.getItems());
    }
}
