package com.ydhl.micro.client.admin.controller.sys;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.api.dto.admin.goods.category.ResponseCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.label.ResponseLabelDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ResponseDicitemDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.consts.State;
import com.ydhl.micro.core.consts.RedisKeyConst;
import com.ydhl.micro.core.util.CacheHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SysCacheController
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-11 11:39:42
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "数据字典缓存获取接口")
public class SysCacheClient {

    @Autowired
    private CacheHelper cacheHelper;

    /** 从缓存中获取数据字典列表 参数 字典类型 typeCode */
    @ApiOperation(value = "从缓存中获取数据字典列表", notes = "参数 字典类型 typeCode")
    @RequestMapping(value = "cache/dicitem/{typeCode}", method = RequestMethod.GET)
    public HttpResultDTO dicitemList(@PathVariable(name = "typeCode") String typeCode) {
        Map<String, String> result = new HashMap<>();
        Map<String, ResponseDicitemDTO> dicitemDTOMap = cacheHelper.getDictCodeMap(typeCode);
        if (CollectionUtil.isNotEmpty(dicitemDTOMap)) {
            for (Map.Entry<String, ResponseDicitemDTO> entry : dicitemDTOMap.entrySet()) {
                if (entry.getValue().getState().equalsIgnoreCase(State.NORMAL.name())) {
                    result.put(entry.getKey(), entry.getValue().getItemValue());
                }
            }
        }
        return HttpResultDTO.ok(result);
    }

    /** 从缓存中获取数据字典列表 参数 字典类型 typeCode */
    @ApiOperation(value = "从缓存中获取数据字典列表", notes = "参数 字典类型 typeCode")
    @RequestMapping(value = "cache/dicitemCode/{typeCode}", method = RequestMethod.GET)
    public HttpResultDTO dicitemCodeList(@PathVariable(name = "typeCode") String typeCode) {
        JSONArray result = new JSONArray();
        Map<String, ResponseDicitemDTO> dicitemDTOMap = cacheHelper.getDictCodeMap(typeCode);
        if (CollectionUtil.isNotEmpty(dicitemDTOMap)) {
            for (Map.Entry<String, ResponseDicitemDTO> entry : dicitemDTOMap.entrySet()) {
                if (entry.getValue().getState().equalsIgnoreCase(State.NORMAL.name())) {
                    JSONObject dic = new JSONObject();
                    dic.put("key", entry.getKey());
                    dic.put("value", entry.getValue().getItemValue());
                    result.add(dic);
                }
            }
        }
        return HttpResultDTO.ok(result);
    }

    /** 从缓存中获取数据字典列表(ID主键) 参数 字典类型 typeCode */
    @ApiOperation(value = "从缓存中获取数据字典列表", notes = "参数 字典类型 typeCode")
    @RequestMapping(value = "cache/dicitemId/{typeCode}", method = RequestMethod.GET)
    public HttpResultDTO dicitemIdList(@PathVariable(name = "typeCode") String typeCode) {
        JSONArray result = new JSONArray();
        Map<String, ResponseDicitemDTO> dicitemDTOMap = cacheHelper.getDictIdMap(typeCode);
        if (CollectionUtil.isNotEmpty(dicitemDTOMap)) {
            for (Map.Entry<String, ResponseDicitemDTO> entry : dicitemDTOMap.entrySet()) {
                if (entry.getValue().getState().equalsIgnoreCase(State.NORMAL.name())) {
                    JSONObject dic = new JSONObject();
                    dic.put("key", entry.getKey());
                    dic.put("value", entry.getValue().getItemValue());
                    result.add(dic);
                }
            }
        }
        return HttpResultDTO.ok(result);
    }

    /** 从缓存中获取数据字典值 参数1 字典类型 typeCode,参数2 字段项编码 itemCode */
    @ApiOperation(value = "从缓存中获取数据字典值", notes = "参数1 字典类型 typeCode,参数2 字段项编码 itemCode")
    @RequestMapping(value = "cache/dicitem/{typeCode}/{itemCode}", method = RequestMethod.GET)
    public HttpResultDTO dicitemValue(@PathVariable(name = "typeCode") String typeCode, @PathVariable(name = "itemCode") String itemCode) {
        ResponseDicitemDTO dicitemDTO = cacheHelper.getDictItemByCode(typeCode, itemCode);
        if (dicitemDTO.getState().equalsIgnoreCase(State.NORMAL.name())) {
            return HttpResultDTO.ok(dicitemDTO.getItemValue());
        } else {
            return HttpResultDTO.ok();
        }
    }

    /** 从缓存中取商品类型列表 */
    @ApiOperation(value = "从缓存中取商品类型列表")
    @RequestMapping(value = "cache/categorys", method = RequestMethod.GET)
    public HttpResultDTO pmsCategorys(@RequestParam(name = "type", defaultValue = "code") String type) {
        String key = StringUtils.equalsIgnoreCase(type, "code") ? RedisKeyConst.GOODS_CATEGORY_CODE_MAP_KEY : RedisKeyConst.GOODS_CATEGORY_ID_MAP_KEY;
        JSONArray result = new JSONArray();
        Map<String, ResponseCategoryDTO> categoryDTOMap = null;
        if (StringUtils.equalsIgnoreCase(key, RedisKeyConst.GOODS_CATEGORY_CODE_MAP_KEY)) {
            categoryDTOMap = cacheHelper.getGoodsCategoryCodeMap();
        } else {
            categoryDTOMap = cacheHelper.getGoodsCategoryIdMap();
        }
        if (CollectionUtil.isNotEmpty(categoryDTOMap)) {
            for (Map.Entry<String, ResponseCategoryDTO> entry : categoryDTOMap.entrySet()) {
                JSONObject dic = new JSONObject();
                dic.put("key", entry.getKey());
                dic.put("value", entry.getValue().getName());
                result.add(dic);
            }
        }
        return HttpResultDTO.ok(result);
    }

    /** 从缓存中取商品类型列表 */
    @ApiOperation(value = "从缓存中取商品类型列表")
    @RequestMapping(value = "public/cache/categoryList", method = RequestMethod.GET)
    public HttpResultDTO pmsCategorys() {
        Map<String, ResponseCategoryDTO> categoryDTOMap = cacheHelper.getGoodsCategoryIdMap();
        if (CollectionUtil.isNotEmpty(categoryDTOMap)) {
            return HttpResultDTO.ok(categoryDTOMap.values());
        } else {
            return HttpResultDTO.ok();
        }
    }

    /** 从缓存中取商品标签列表 */
    @ApiOperation(value = "从缓存中取商品标签列表")
    @RequestMapping(value = "public/cache/labels", method = RequestMethod.GET)
    public HttpResultDTO pmsLables() {
        Map<String, ResponseLabelDTO> labelDTOMap = cacheHelper.getGoodsLabelIdMap();
        if (CollectionUtil.isNotEmpty(labelDTOMap)) {
            return HttpResultDTO.ok(labelDTOMap.values());
        } else {
            return HttpResultDTO.ok();
        }
    }

    /** 从缓存中取上次同步类特征日期 */
    @ApiOperation(value = "从缓存中取上次同步类特征日期")
    @RequestMapping(value = "cache/lastSyncDate", method = RequestMethod.GET)
    public HttpResultDTO lastSyncDate() {
        return HttpResultDTO.ok(cacheHelper.getStringValue(RedisKeyConst.LAST_SYNC_SAP_CLASS_KEY));
    }

}
