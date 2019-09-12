package com.ydhl.micro.base.controller.sys;

import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.role.*;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.sys.SysRoleApi;
import com.ydhl.micro.base.service.AuthenticationService;
import com.ydhl.micro.base.service.SysRoleService;
import com.ydhl.micro.core.consts.ModuleEnum;
import com.ydhl.micro.core.consts.OperationEnum;
import com.ydhl.micro.core.util.OperationLog;
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
public class SysRoleController implements SysRoleApi {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private SysRoleService roleService;

    @Override
    public HttpResultDTO roleResourceTree(Long id) {
        return HttpResultDTO.ok(authenticationService.roleResourceTree(id));
    }

    @Override
    public HttpResultDTO findById(Long id) {
        return HttpResultDTO.ok(roleService.findById(id));
    }

    @Override
    public HttpPageResultDTO pageSearch(SearchRoleDTO searchDTO) {
        PageInfo<ResponseRoleDTO> pageInfo = roleService.pageSearch(searchDTO);
        return HttpPageResultDTO.ok(pageInfo.getList(), pageInfo.getPageSize(), pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getTotal());
    }

    @Override
    public HttpResultDTO search(SearchRoleDTO searchDTO) {
        return HttpResultDTO.ok(roleService.search(searchDTO));
    }

    @Override
    @OperationLog(module = ModuleEnum.角色管理, operation = OperationEnum.新增, remark = "添加角色")
    public HttpResultDTO create(CreateRoleDTO dto) {
        roleService.create(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.角色管理, operation = OperationEnum.编辑, remark = "修改角色")
    public HttpResultDTO modify(ModifyRoleDTO dto) {
        roleService.modify(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.角色管理, operation = OperationEnum.删除, remark = "删除角色")
    public HttpResultDTO delete(List<Long> idList) {
        roleService.delete(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.角色管理, operation = OperationEnum.冻结, remark = "冻结角色")
    public HttpResultDTO frozen(List<Long> idList) {
        roleService.frozen(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.角色管理, operation = OperationEnum.启用, remark = "启用角色")
    public HttpResultDTO enable(List<Long> idList) {
        roleService.enable(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.角色管理, operation = OperationEnum.分配资源, remark = "分配资源列表")
    public HttpResultDTO assignResource(AssignResourceDTO dto) {
        roleService.assignResource(dto);
        return HttpResultDTO.ok();
    }
}
