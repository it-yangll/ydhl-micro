package com.ydhl.micro.base.controller.sys;

import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.post.AssignRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.post.SearchPostDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.sys.SysPostApi;
import com.ydhl.micro.base.service.SysPostService;
import com.ydhl.micro.core.consts.ModuleEnum;
import com.ydhl.micro.core.consts.OperationEnum;
import com.ydhl.micro.core.util.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysLogController
 * @Description TODO
 * @Author Ly
 * @Date 2019/3/23 17:59
 * @Version 1.0
 **/
@RestController
@Slf4j
public class SysPostController implements SysPostApi {

    @Autowired
    private SysPostService service;

    @Override
    public HttpPageResultDTO pageSearch(SearchPostDTO searchDTO) {
        PageInfo pageInfo = service.pageSearch(searchDTO);
        return HttpPageResultDTO.ok(pageInfo.getList(), pageInfo.getPageSize(), pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getTotal());
    }

    @Override
    public HttpResultDTO search(SearchPostDTO searchDTO) {
        return HttpResultDTO.ok(service.pageSearch(searchDTO));
    }

    @Override
    @OperationLog(module = ModuleEnum.职位管理, operation = OperationEnum.分配角色, remark = "给职位分配角色")
    public HttpResultDTO assignRole(AssignRoleDTO dto) {
        service.assignRole(dto);
        return HttpResultDTO.ok();
    }
}
