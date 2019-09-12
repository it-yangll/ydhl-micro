package com.ydhl.micro.client.admin.controller.pms;

import com.ydhl.micro.api.dto.admin.goods.category.CreateCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.category.ModifyCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.category.SearchCategoryDTO;
import com.ydhl.micro.api.dto.admin.goods.category.SearchCategoryPageDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.pms.PmsCategoryFeignClient;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
  *商品类型管理APIClient
  *@Author: yangll
  *@Date: 2019/5/5 9:35
  *@Version: 1.0
  */
@RestController
@Slf4j
public class PmsCategoryClient {

    @Autowired
    private PmsCategoryFeignClient pmsCategoryFeignClient;


    /**
     *@Description: 单条查询
     *@Param: [id]
     *@method: findById
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "单条查询")
    @RequestMapping(value = "goods/category/{id}", method = RequestMethod.GET)
    public HttpResultDTO findById(@PathVariable(value = "id",required = true) Long id) {
        return pmsCategoryFeignClient.findById(id);
    }

    /**
     *@Description: 分页条件查询
     *@Param: [searchDTO]
     *@method: pageSearch
     *@return: com.ybzt.micro.api.dto.common.HttpPageResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "goods/category/pageData", method = RequestMethod.POST)
    public HttpPageResultDTO pageSearch(@RequestBody SearchCategoryPageDTO searchDTO) {
        return pmsCategoryFeignClient.pageSearch(searchDTO);
    }

    /**
     *@Description: 条件查询
     *@Param: [searchDTO]
     *@method: search
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "条件查询")
    @RequestMapping(value = "goods/category/data", method = RequestMethod.POST)
    public HttpResultDTO search(@RequestBody SearchCategoryDTO searchDTO) {
        return pmsCategoryFeignClient.search(searchDTO);
    }

    /**
     *@Description: 获取商品类型下拉
     *@Param: []
     *@method: searchList
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/29
     */
    @ApiOperation(value = "获取商品类型下拉")
    @RequestMapping(value = "goods/category/searchList", method = RequestMethod.GET)
    public HttpResultDTO searchList() {
        return pmsCategoryFeignClient.searchList();
    }

    /**
     *@Description: 新增
     *@Param: [dto]
     *@method: create
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "新增")
    @RequestMapping(value = "goods/category/add", method = RequestMethod.POST)
    public HttpResultDTO create(@Valid @RequestBody CreateCategoryDTO dto) {
        return pmsCategoryFeignClient.create(dto);
    }

    /**
     *@Description: 修改
     *@Param: [dto]
     *@method: modify
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "修改")
    @RequestMapping(value = "goods/category/modifiy", method = RequestMethod.POST)
    public HttpResultDTO modify(@Valid @RequestBody ModifyCategoryDTO dto) {
        return pmsCategoryFeignClient.modify(dto);
    }


    /**
     *@Description: 批量操作-删除
     *@Param: [idList]
     *@method: delete
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "批量操作-删除")
    @RequestMapping(value = "goods/category/delete", method = RequestMethod.POST)
    public HttpResultDTO delete(@RequestBody(required = true) List<Long> idList){
        return pmsCategoryFeignClient.delete(idList);
    }


























}
