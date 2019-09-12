package com.ydhl.micro.client.admin.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.user.*;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.sys.SysUserFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName AuthenticationController
 * @Description 用户管理
 * @Author yangll
 * @Date 2019-9-11 11:43:48
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "用户管理相关接口")
public class SysUserClient {

    @Autowired
    private SysUserFeignClient feignClient;

    /** 单条查询 */
    @ApiOperation("根据用户id获取用户数据")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public HttpResultDTO<ResponseUserDTO> findById(@ApiParam(name = "id", value = "用户ID", required = true) @PathVariable Long id) {
        return feignClient.findById(id);
    }

    /** 分页条件查询 */
    @ApiOperation(value = "查询用户列表（分页）")
    @RequestMapping(value = "user/pageData", method = RequestMethod.POST)
    public HttpPageResultDTO<ResponseUserDTO> pageSearch(@ApiParam(value = "带分页的查询条件") @RequestBody SearchUserDTO searchDTO) {
        return feignClient.pageSearch(searchDTO);
    }

    /** 条件查询 */
    @ApiOperation(value = "查询用户列表（不分页）")
    @RequestMapping(value = "user/data", method = RequestMethod.POST)
    public HttpResultDTO<ResponseUserDTO> search(@ApiParam(value = "查询条件") @RequestBody SearchUserDTO searchDTO) {
        return feignClient.search(searchDTO);
    }

    /** 新增 */
    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public HttpResultDTO create(@ApiParam(value = "添加的用户信息", required = true) @RequestBody CreateUserDTO dto) {
        return feignClient.create(dto);
    }

    /** 修改 */
    @ApiOperation(value = "修改用户")
    @RequestMapping(value = "user/modifiy", method = RequestMethod.POST)
    public HttpResultDTO modify(@ApiParam(value = "修改的用户信息", required = true) @RequestBody ModifyUserDTO dto) {
        return feignClient.modify(dto);
    }

    /** 批量操作-删除 */
    @ApiOperation(value = "批量删除用户", notes = "以数组形式传递用户id")
    @RequestMapping(value = "user/delete", method = RequestMethod.POST)
    public HttpResultDTO delete(@ApiParam(value = "用户ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.delete(idList);
    }

    /** 批量操作-冻结 */
    @ApiOperation(value = "批量冻结用户", notes = "以数组形式传递用户id")
    @RequestMapping(value = "user/frozen", method = RequestMethod.POST)
    public HttpResultDTO frozen(@ApiParam(value = "用户ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.frozen(idList);
    }

    /** 批量操作-启用 */
    @ApiOperation(value = "批量启用用户", notes = "以数组形式传递用户id")
    @RequestMapping(value = "user/enable", method = RequestMethod.POST)
    public HttpResultDTO enable(@ApiParam(value = "用户ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.enable(idList);
    }

    /** 批量操作-重置密码 */
    @ApiOperation(value = "批量重置密码", notes = "以数组形式传递用户id；重置后的密码为123456")
    @RequestMapping(value = "user/reset", method = RequestMethod.POST)
    public HttpResultDTO resetPwd(@ApiParam(value = "用户ID集合", required = true) @RequestBody List<Long> idList) {
        return feignClient.resetPwd(idList);
    }

    /** 分配角色 */
    @ApiOperation(value = "分配角色给用户", notes = "只支持给单个用户分配角色")
    @RequestMapping(value = "user/assignRole", method = RequestMethod.POST)
    public HttpResultDTO assignRole(@ApiParam(value = "用户分配角色信息", required = true) @RequestBody AssignRoleDTO dto) {
        return feignClient.assignRole(dto);
    }

    /** 修改密码 */
    @ApiOperation(value = "修改用户密码")
    @RequestMapping(value = "user/updatePwd", method = RequestMethod.POST)
    public HttpResultDTO updatePwd(@ApiParam(value = "修改用户密码信息", required = true) @Valid @RequestBody UpdatePwdDTO dto) {
        return feignClient.updatePwd(dto);
    }
}
