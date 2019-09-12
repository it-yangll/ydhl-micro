package com.ydhl.micro.base.controller.sys;

import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.user.*;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.sys.SysUserApi;
import com.ydhl.micro.base.dao.auto.SysUserMapper;
import com.ydhl.micro.base.entity.SysUser;
import com.ydhl.micro.base.service.AuthenticationService;
import com.ydhl.micro.base.service.SysUserService;
import com.ydhl.micro.core.consts.ModuleEnum;
import com.ydhl.micro.core.consts.OperationEnum;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.OperationLog;
import com.ydhl.micro.core.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName AuthenticationController
 * @Description TODO
 * @Author Ly
 * @Date 2019/3/23 17:59
 * @Version 1.0
 **/
@RestController
@Slf4j
public class SysUserController implements SysUserApi {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public HttpResultDTO navTree() {
        return HttpResultDTO.ok(authenticationService.navTree());
    }

    @Override
    public HttpResultDTO findById(Long id) {
        return HttpResultDTO.ok(userService.findById(id));
    }

    @Override
    public HttpPageResultDTO pageSearch(SearchUserDTO searchDTO) {
        PageInfo<ResponseUserDTO> pageInfo = userService.pageSearch(searchDTO);
        return HttpPageResultDTO.ok(pageInfo.getList(), pageInfo.getPageSize(), pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getTotal());
    }

    @Override
    public HttpResultDTO search(SearchUserDTO searchDTO) {
        return HttpResultDTO.ok(userService.search(searchDTO));
    }

    @Override
    @OperationLog(module = ModuleEnum.用户管理, operation = OperationEnum.新增, remark = "添加系统账号")
    public HttpResultDTO create(CreateUserDTO dto) {
        userService.create(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.用户管理, operation = OperationEnum.编辑, remark = "修改系统账号")
    public HttpResultDTO modify(ModifyUserDTO dto) {
        userService.modify(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.用户管理, operation = OperationEnum.删除, remark = "删除系统账号")
    public HttpResultDTO delete(List<Long> idList) {
        userService.delete(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.用户管理, operation = OperationEnum.冻结, remark = "冻结系统账号")
    public HttpResultDTO frozen(List<Long> idList) {
        userService.frozen(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.用户管理, operation = OperationEnum.启用, remark = "启用系统账号")
    public HttpResultDTO enable(List<Long> idList) {
        userService.enable(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.用户管理, operation = OperationEnum.重置密码, remark = "重置账号密码")
    public HttpResultDTO resetPwd(List<Long> idList) {
        userService.resetPwd(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.用户管理, operation = OperationEnum.分配角色, remark = "给账号分配角色")
    public HttpResultDTO assignRole(AssignRoleDTO dto) {
        userService.assignRole(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.用户管理, operation = OperationEnum.修改密码, remark = "修改当前密码")
    public HttpResultDTO updatePwd(UpdatePwdDTO dto) {
        userService.updatePwd(dto);
        return HttpResultDTO.ok();
    }

    @Override
    public List<String> getUserPermissions() {
        SysUser user = userMapper.selectByPrimaryKey(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        return authenticationService.getUserPermissions(user);
    }

    @Override
    public List<String> getUserRoles() {
        SysUser user = userMapper.selectByPrimaryKey(JwtUtil.parseToJwtBody(TokenUtil.getToken()).getPk());
        return authenticationService.getRoles(user);
    }
}
