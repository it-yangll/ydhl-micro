package com.ydhl.micro.base.controller.sys;

import com.ydhl.micro.api.dto.admin.sys.resource.CreateResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ModifyResourceDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.sys.SysResourceApi;
import com.ydhl.micro.base.service.SysResourceService;
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
public class SysResourceController implements SysResourceApi {

    @Autowired
    private SysResourceService service;

    @Override
    public HttpResultDTO resourceTree() {
        return HttpResultDTO.ok(service.resourceTree());
    }

    @Override
    public HttpResultDTO findById(Long id) {
        return HttpResultDTO.ok(service.findById(id));
    }

    @Override
    @OperationLog(module = ModuleEnum.资源管理, operation = OperationEnum.新增, remark = "添加资源")
    public HttpResultDTO create(CreateResourceDTO dto) {
        service.create(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.资源管理, operation = OperationEnum.编辑, remark = "修改资源")
    public HttpResultDTO modify(ModifyResourceDTO dto) {
        service.modify(dto);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.资源管理, operation = OperationEnum.删除, remark = "删除资源")
    public HttpResultDTO delete(List<Long> idList) {
        service.delete(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.资源管理, operation = OperationEnum.冻结, remark = "冻结资源")
    public HttpResultDTO frozen(List<Long> idList) {
        service.frozen(idList);
        return HttpResultDTO.ok();
    }

    @Override
    @OperationLog(module = ModuleEnum.资源管理, operation = OperationEnum.启用, remark = "启用资源")
    public HttpResultDTO enable(List<Long> idList) {
        service.enable(idList);
        return HttpResultDTO.ok();
    }

}
