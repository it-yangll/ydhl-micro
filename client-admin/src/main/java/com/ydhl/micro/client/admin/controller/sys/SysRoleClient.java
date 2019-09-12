package com.ydhl.micro.client.admin.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.role.AssignResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.role.CreateRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.role.ModifyRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.role.SearchRoleDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.sys.SysRoleFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AuthenticationController
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-11 11:43:20
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "角色管理相关接口")
public class SysRoleClient {

    @Autowired
    private SysRoleFeignClient feignClient;

    /** 单条查询 */
    @ApiOperation("根据用户id获取角色数据")
    @RequestMapping(value = "role/{id}", method = RequestMethod.GET)
    public HttpResultDTO findById(@ApiParam(name = "id", value = "用户ID", required = true) @PathVariable Long id) {
        return feignClient.findById(id);
    }

    /** 分页条件查询 */
    @ApiOperation(value = "查询角色列表（分页）")
    @RequestMapping(value = "role/pageData", method = RequestMethod.POST)
    public HttpPageResultDTO pageSearch(@ApiParam(value = "带分页的查询条件") @RequestBody SearchRoleDTO searchDTO) {
        return feignClient.pageSearch(searchDTO);
    }

    /** 条件查询 */
    @ApiOperation(value = "查询角色列表（不分页）")
    @RequestMapping(value = "role/data", method = RequestMethod.POST)
    public HttpResultDTO search(@ApiParam(value = "查询条件") @RequestBody SearchRoleDTO searchDTO) {
        return feignClient.search(searchDTO);
    }

    /** 新增 */
    @ApiOperation(value = "添加角色")
    @RequestMapping(value = "role/add", method = RequestMethod.POST)
    public HttpResultDTO create(@ApiParam(value = "添加的角色信息", required = true) @RequestBody CreateRoleDTO dto) {
        return feignClient.create(dto);
    }

    /** 修改 */
    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "role/modifiy", method = RequestMethod.POST)
    public HttpResultDTO modify(@ApiParam(value = "修改的角色信息", required = true) @RequestBody ModifyRoleDTO dto) {
        return feignClient.modify(dto);
    }

    /** 批量操作-删除 */
    @ApiOperation(value = "批量删除角色", notes = "以数组形式传递用户id")
    @RequestMapping(value = "role/delete", method = RequestMethod.POST)
    public HttpResultDTO delete(@ApiParam(value = "角色ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.delete(idList);
    }

    /** 批量操作-冻结 */
    @ApiOperation(value = "批量冻结角色", notes = "以数组形式传递角色id")
    @RequestMapping(value = "role/frozen", method = RequestMethod.POST)
    public HttpResultDTO frozen(@ApiParam(value = "角色ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.frozen(idList);
    }

    /** 批量操作-启用 */
    @ApiOperation(value = "批量启用角色", notes = "以数组形式传递角色id")
    @RequestMapping(value = "role/enable", method = RequestMethod.POST)
    public HttpResultDTO enable(@ApiParam(value = "角色ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.enable(idList);
    }

    /** 分配角色 */
    @ApiOperation(value = "角色分配资源", notes = "只支持给单个角色分配资源")
    @RequestMapping(value = "role/assignResource", method = RequestMethod.POST)
    public HttpResultDTO assignResource(@ApiParam(value = "角色资源分配角色信息", required = true) @RequestBody AssignResourceDTO dto) {
        return feignClient.assignResource(dto);
    }

}
