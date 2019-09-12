package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.dto.admin.sys.resource.CreateResourceDTO;
import com.ydhl.micro.api.dto.admin.sys.resource.ModifyResourceDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysResourceFeignClientFallback
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 18:49
 * @Version 1.0
 **/
@Component
public class SysResourceFeignClientFallback implements SysResourceFeignClient {
    @Override
    public HttpResultDTO findById(Long id) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO resourceTree() {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO create(@Valid CreateResourceDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO modify(@Valid ModifyResourceDTO dto) {
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
}
