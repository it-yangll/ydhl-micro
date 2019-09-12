package com.ydhl.micro.api.enpoint.admin.goods;

import com.ydhl.micro.api.dto.admin.goods.label.CreateLabelDTO;
import com.ydhl.micro.api.dto.admin.goods.label.ModifyLabelDTO;
import com.ydhl.micro.api.dto.admin.goods.label.SearchLabelDTO;
import com.ydhl.micro.api.dto.admin.goods.label.SearchLabelPageDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
  *商品标签接口转发
  *@Author: yangll
  *@Date: 2019/4/29 17:31
  *@Version: 1.0
  */
public interface PmsLabelApi {

    /** 单条查询 */
    @RequestMapping(value = "goods/label/{id}", method = RequestMethod.GET)
    HttpResultDTO findById(@PathVariable(name = "id") Long id);

    /** 分页条件查询 */
    @RequestMapping(value = "goods/label/pageData", method = RequestMethod.POST)
    HttpPageResultDTO pageSearch(@RequestBody SearchLabelPageDTO searchDTO);

    /** 条件查询 */
    @RequestMapping(value = "goods/label/data", method = RequestMethod.POST)
    HttpResultDTO search(@RequestBody SearchLabelDTO searchDTO);

    /** 新增 */
    @RequestMapping(value = "goods/label/add", method = RequestMethod.POST)
    HttpResultDTO create(@Valid @RequestBody CreateLabelDTO dto);

    /** 修改 */
    @RequestMapping(value = "goods/label/modifiy", method = RequestMethod.POST)
    HttpResultDTO modify(@Valid @RequestBody ModifyLabelDTO dto);

    /** 批量操作-删除 */
    @RequestMapping(value = "goods/label/delete", method = RequestMethod.POST)
    HttpResultDTO delete(@RequestBody List<Long> idList);


























}
