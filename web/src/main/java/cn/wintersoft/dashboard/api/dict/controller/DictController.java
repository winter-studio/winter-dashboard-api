package cn.wintersoft.dashboard.api.dict.controller;

import cn.wintersoft.dashboard.api.dict.entity.Dict;
import cn.wintersoft.dashboard.api.dict.entity.DictItem;
import cn.wintersoft.dashboard.api.dict.model.DictModel;
import cn.wintersoft.dashboard.api.dict.service.DictItemService;
import cn.wintersoft.dashboard.api.dict.service.DictService;
import cn.wintersoft.dashboard.web.model.ApiRes;
import cn.wintersoft.dashboard.web.resolver.JsonParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Kyun
 * @since 2022-07-22
 */
@RestController
@RequestMapping("/dicts")
@RequiredArgsConstructor
@Tag(name = "字典模块")
public class DictController {

    private final DictService dictService;
    private final DictItemService dictItemService;

    @Operation(summary = "获取所有字典")
    @GetMapping
    public ApiRes<List<Dict>> getDicts() {
        return ApiRes.success(dictService.list());
    }

    @Operation(summary = "获取字典")
    @GetMapping("/{code}")
    public ApiRes<DictModel> getDict(@PathVariable("code") String code) {
        return ApiRes.success(dictService.getDict(code));
    }

    @Operation(summary = "保存字典")
    @PostMapping
    public ApiRes<Void> saveDict(@Validated @RequestBody DictModel dictModel) {
        dictService.saveDict(dictModel);
        return ApiRes.success();
    }

    @Operation(summary = "获取字典项")
    @GetMapping("/{code}/items")
    public ApiRes<List<DictItem>> getDictItems(@PathVariable("code") String code) {
        return ApiRes.success(dictItemService.getDictItems(code));
    }

    @Operation(summary = "删除字典")
    @DeleteMapping
    public ApiRes<Void> deleteRoles(@JsonParam("codes") List<String> codes) {
        dictService.deleteDicts(codes);
        return ApiRes.success();
    }

}
