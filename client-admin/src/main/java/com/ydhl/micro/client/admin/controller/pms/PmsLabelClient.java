package com.ydhl.micro.client.admin.controller.pms;

import com.ydhl.micro.api.dto.admin.goods.label.CreateLabelDTO;
import com.ydhl.micro.api.dto.admin.goods.label.ModifyLabelDTO;
import com.ydhl.micro.api.dto.admin.goods.label.SearchLabelDTO;
import com.ydhl.micro.api.dto.admin.goods.label.SearchLabelPageDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.pms.PmsLabelFeignClient;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
  *商品标签管理APIClient
  *@Author: yangll
  *@Date: 2019/5/5 9:35
  *@Version: 1.0
  */
@RestController
@Slf4j
public class PmsLabelClient {

    @Autowired
    private PmsLabelFeignClient pmsLabelFeignClient;


    /**
     *@Description: 单条查询
     *@Param: [id]
     *@method: findById
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "批量操作删除 ")
    @RequestMapping(value = "goods/label/{id}", method = RequestMethod.GET)
    public HttpResultDTO findById(@PathVariable(value = "id",required = true) Long id) {
        return pmsLabelFeignClient.findById(id);
    }

    /**
     *@Description: 分页条件查询
     *@Param: [searchDTO]
     *@method: pageSearch
     *@return: com.ybzt.micro.api.dto.common.HttpPageResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "分页条件查询 ")
    @RequestMapping(value = "goods/label/pageData", method = RequestMethod.POST)
    public HttpPageResultDTO pageSearch(@RequestBody SearchLabelPageDTO searchDTO) {
        return pmsLabelFeignClient.pageSearch(searchDTO);
    }

    /**
     *@Description: 条件查询
     *@Param: [searchDTO]
     *@method: search
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "条件查询 ")
    @RequestMapping(value = "goods/label/data", method = RequestMethod.POST)
    public HttpResultDTO search(@RequestBody SearchLabelDTO searchDTO) {
        return pmsLabelFeignClient.search(searchDTO);
    }

    /**
     *@Description: 新增
     *@Param: [dto]
     *@method: create
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "新增 ")
    @RequestMapping(value = "goods/label/add", method = RequestMethod.POST)
    public HttpResultDTO create(@Valid @RequestBody CreateLabelDTO dto) {
        return pmsLabelFeignClient.create(dto);
    }

    /**
     *@Description: 修改
     *@Param: [dto]
     *@method: modify
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "修改 ")
    @RequestMapping(value = "goods/label/modifiy", method = RequestMethod.POST)
    public HttpResultDTO modify(@Valid @RequestBody ModifyLabelDTO dto) {
        return pmsLabelFeignClient.modify(dto);
    }

    /**
     *@Description: 批量操作-删除
     *@Param: [idList]
     *@method: delete
     *@return: com.ybzt.micro.api.dto.common.HttpResultDTO
     *@Author: yangll
     *@date: 2019/5/27
     */
    @ApiOperation(value = "批量操作删除 ")
    @RequestMapping(value = "goods/label/delete", method = RequestMethod.POST)
    public HttpResultDTO delete(@RequestBody(required = true) List<Long> idList){
        return pmsLabelFeignClient.delete(idList);
    }


























}
