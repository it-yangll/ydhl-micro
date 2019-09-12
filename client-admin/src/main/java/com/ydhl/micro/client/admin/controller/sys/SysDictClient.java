package com.ydhl.micro.client.admin.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.dict.CreateDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ModifyDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.SearchDicitemDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.sys.SysDictFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysDictClient
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-11 11:42:15
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "数据字典管理相关接口")
public class SysDictClient {

    @Autowired
    private SysDictFeignClient feignClient;

    /** 数据字典类别 */
    @ApiOperation(value = "获取数据字典类别", notes = "下拉列表使用")
    @RequestMapping(value = "dic/type", method = RequestMethod.GET)
    HttpResultDTO findDicTypeList() {
        return feignClient.findDicTypeList();
    }

    /** 单条查询 */
    @ApiOperation("根据用户id获取数据字典单条信息")
    @RequestMapping(value = "dic/{id}", method = RequestMethod.GET)
    HttpResultDTO findById(@ApiParam(name = "id", value = "数据字典ID", required = true) @PathVariable Long id) {
        return feignClient.findById(id);
    }

    /** 分页条件查询 */
    @ApiOperation(value = "查询数据字典列表（分页）")
    @RequestMapping(value = "dic/pageData", method = RequestMethod.POST)
    HttpPageResultDTO pageSearch(@ApiParam(value = "带分页的查询条件") @RequestBody SearchDicitemDTO searchDTO) {
        return feignClient.pageSearch(searchDTO);
    }

    /** 条件查询 */
    @ApiOperation(value = "查询数据字典列表（不分页）")
    @RequestMapping(value = "dic/data", method = RequestMethod.POST)
    HttpResultDTO search(@ApiParam(value = "查询条件") @RequestBody SearchDicitemDTO searchDTO) {
        return feignClient.search(searchDTO);
    }

    /** 新增 */
    @ApiOperation(value = "添加数据字典项")
    @RequestMapping(value = "dic/add", method = RequestMethod.POST)
    HttpResultDTO create(@ApiParam(value = "添加的数据字典信息", required = true) @Valid @RequestBody CreateDicitemDTO dto) {
        return feignClient.create(dto);
    }

    /** 修改 */
    @ApiOperation(value = "修改数据字典")
    @RequestMapping(value = "dic/modifiy", method = RequestMethod.POST)
    HttpResultDTO modify(@Valid @RequestBody ModifyDicitemDTO dto) {
        return feignClient.modify(dto);
    }

    /** 批量操作-删除 */
    @ApiOperation(value = "批量操作-删除 ", notes = "以数组形式传递用户id")
    @RequestMapping(value = "dic/delete", method = RequestMethod.POST)
    HttpResultDTO delete(@ApiParam(value = "数据字典ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.delete(idList);
    }

    /** 批量操作-冻结 */
    @ApiOperation(value = "批量操作-冻结", notes = "以数组形式传递用户id")
    @RequestMapping(value = "dic/frozen", method = RequestMethod.POST)
    HttpResultDTO frozen(@ApiParam(value = "数据字典ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.frozen(idList);
    }

    /** 批量操作-启用 */
    @ApiOperation(value = "批量操作-启用", notes = "以数组形式传递用户id")
    @RequestMapping(value = "dic/enable", method = RequestMethod.POST)
    HttpResultDTO enable(@ApiParam(value = "数据字典ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.enable(idList);
    }
}
