package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.dto.admin.sys.dept.AssignRoleDTO;
import com.ydhl.micro.api.dto.admin.sys.dept.CreateDeptDTO;
import com.ydhl.micro.api.dto.admin.sys.dept.ModifyDeptDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysDeptFeignClientFallback
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 17:21
 * @Version 1.0
 **/
@Component
public class SysDeptFeignClientFallback implements SysDeptFeignClient {
    @Override
    public HttpResultDTO findById(Long id) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO deptTree() {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO deptList() {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO create(@Valid CreateDeptDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO modify(@Valid ModifyDeptDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO delete(List<Long> idList) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO frozen(List<Long> idList) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO enable(List<Long> idList) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO assignRole(@Valid AssignRoleDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }
}
