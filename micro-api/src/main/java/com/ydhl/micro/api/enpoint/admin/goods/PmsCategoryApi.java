package com.ydhl.micro.api.enpoint.admin.goods;

import com.ydhl.micro.api.dto.admin.goods.category.CreateCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.category.ModifyCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.category.SearchCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.category.SearchCategoryPageDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
  *商品接口转发
  *@Author: yangll
  *@Date: 2019/4/29 17:31
  *@Version: 1.0
  */
public interface PmsCategoryApi {

    @ApiOperation(value = "单条查询")
    @RequestMapping(value = "goods/category/{id}", method = RequestMethod.GET)
    HttpResultDTO findById(@PathVariable(name = "id") Long id);

    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "goods/category/pageData", method = RequestMethod.POST)
    HttpPageResultDTO pageSearch(@RequestBody SearchCategoryPageDTO searchDTO);

    @ApiOperation(value = "条件查询")
    @RequestMapping(value = "goods/category/data", method = RequestMethod.POST)
    HttpResultDTO search(@RequestBody SearchCategoryDTO searchDTO);

    @ApiOperation(value = "获取商品类型下拉")
    @RequestMapping(value = "goods/category/searchList", method = RequestMethod.GET)
    HttpResultDTO searchList();

    @ApiOperation(value = "新增")
    @RequestMapping(value = "goods/category/add", method = RequestMethod.POST)
    HttpResultDTO create(@Valid @RequestBody CreateCategoryDTO dto);

    @ApiOperation(value = "修改")
    @RequestMapping(value = "goods/category/modifiy", method = RequestMethod.POST)
    HttpResultDTO modify(@Valid @RequestBody ModifyCategoryDTO dto);

    @ApiOperation(value = "批量操作-删除")
    @RequestMapping(value = "goods/category/delete", method = RequestMethod.POST)
    HttpResultDTO delete(@RequestBody List<Long> idList);


























}
