package com.ydhl.micro.client.admin.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.dept.*;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.sys.SysDeptFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysDeptClient
 * @Description 部门管理
 * @Author yangll
 * @Date 2019-9-11 11:41:54
 * @Version 1.0
 **/
@RestController
@Slf4j
//@Api(description = "部门管理相关接口")
public class SysDeptClient {

    @Autowired
    private SysDeptFeignClient feignClient;

    /** 单条查询 */
    @ApiOperation("根据部门id获取部门数据")
    @RequestMapping(value = "dept/{id}", method = RequestMethod.GET)
    HttpResultDTO<ResponseDeptDTO> findById(@ApiParam(name = "id", value = "部门ID", required = true) @PathVariable Long id) {
        return feignClient.findById(id);
    }

    /** 部门表格树 */
    @ApiOperation(value = "获取所有部门列表（树形表格）", notes = "返回的部门数据是树形结构，前端要以树形表格展示")
    @RequestMapping(value = "dept/tree", method = RequestMethod.GET)
    HttpResultDTO<DeptTreeDTO> deptTree() {
        return feignClient.deptTree();
    }

    /** 条件查询 */
    @ApiOperation(value = "查询部门列表（不分页）")
    @RequestMapping(value = "dept/data", method = RequestMethod.POST)
    HttpResultDTO<ResponseDeptDTO> deptList() {
        return feignClient.deptList();
    }

    /** 新增 */
    @ApiOperation(value = "添加部门", notes = "默认添加的部门是顶级部门，如果点击某一行数据，再添加的话，那么该部门将隶属于点击的部门")
    @RequestMapping(value = "dept/add", method = RequestMethod.POST)
    HttpResultDTO create(@ApiParam(value = "添加的部门信息", required = true) @Valid @RequestBody CreateDeptDTO dto) {
        return feignClient.create(dto);
    }

    /** 修改 */
    @ApiOperation(value = "修改部门", notes = "修改部门信息")
    @RequestMapping(value = "dept/modifiy", method = RequestMethod.POST)
    HttpResultDTO modify(@ApiParam(value = "修改的部门信息", required = true) @Valid @RequestBody ModifyDeptDTO dto) {
        return feignClient.modify(dto);
    }

    /** 批量操作-删除 */
    @ApiOperation(value = "批量删除部门", notes = "以数组形式传递部门id")
    @RequestMapping(value = "dept/delete", method = RequestMethod.POST)
    HttpResultDTO delete(@ApiParam(value = "部门ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.delete(idList);
    }

    /** 批量操作-冻结 */
    @ApiOperation(value = "批量冻结部门", notes = "以数组形式传递部门id")
    @RequestMapping(value = "dept/frozen", method = RequestMethod.POST)
    HttpResultDTO frozen(@ApiParam(value = "部门ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.frozen(idList);
    }

    /** 批量操作-启用 */
    @ApiOperation(value = "批量启用部门", notes = "以数组形式传递部门id")
    @RequestMapping(value = "dept/enable", method = RequestMethod.POST)
    HttpResultDTO enable(@ApiParam(value = "部门ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.enable(idList);
    }

    /** 分配角色 */
    @ApiOperation(value = "分配角色给部门", notes = "只支持给单个部门分配角色")
    @RequestMapping(value = "dept/assignRole", method = RequestMethod.POST)
    HttpResultDTO assignRole(@ApiParam(value = "部门分配角色信息", required = true) @Valid @RequestBody AssignRoleDTO dto) {
        return feignClient.assignRole(dto);
    }
}
