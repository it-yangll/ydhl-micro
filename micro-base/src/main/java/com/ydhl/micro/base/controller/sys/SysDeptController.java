package com.ydhl.micro.base.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.dept.AssignRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.dept.CreateDeptDTO;
import com.ydhl.micro.api.dto.admin.sys.dept.ModifyDeptDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.sys.SysDeptApi;
import com.ydhl.micro.base.service.SysDeptService;
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
public class SysDeptController implements SysDeptApi {

    @Autowired
    private SysDeptService deptService;

    @Override
    public HttpResultDTO deptTree() {
        return HttpResultDTO.ok(deptService.deptTree());
    }

    @Override
    public HttpResultDTO deptList() {
        return HttpResultDTO.ok(deptService.deptList());
    }

    @Override
    public HttpResultDTO findById(Long id) {
        return HttpResultDTO.ok(deptService.findById(id));
    }

    @Override
    @OperationLog(module = ModuleEnum.部门管理, operation = OperationEnum.新增, remark = "添加部门")
    public HttpResultDTO create(CreateDeptDTO dto) {
        deptService.create(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.部门管理, operation = OperationEnum.编辑, remark = "修改部门")
    public HttpResultDTO modify(ModifyDeptDTO dto) {
        deptService.modify(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.部门管理, operation = OperationEnum.删除, remark = "删除部门")
    public HttpResultDTO delete(List<Long> idList) {
        deptService.delete(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.部门管理, operation = OperationEnum.冻结, remark = "冻结部门")
    public HttpResultDTO frozen(List<Long> idList) {
        deptService.frozen(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.部门管理, operation = OperationEnum.启用, remark = "启用部门")
    public HttpResultDTO enable(List<Long> idList) {
        deptService.enable(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.部门管理, operation = OperationEnum.分配角色, remark = "给部门分配角色")
    public HttpResultDTO assignRole(AssignRoleDTO dto) {
        deptService.assignRole(dto);
        return HttpResultDTO.ok();
    }

}
