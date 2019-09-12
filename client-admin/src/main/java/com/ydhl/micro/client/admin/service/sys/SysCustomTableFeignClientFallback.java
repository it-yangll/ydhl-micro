package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.dto.admin.sys.customTable.CreateCustomTableDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * @ClassName SysCustomTableFeignClientFallback
 * @Description TODO
 * @Author Ly
 * @Date 2019/5/8 12:53
 * @Version 1.0
 **/
@Component
public class SysCustomTableFeignClientFallback implements SysCustomTableFeignClient {

    @Override
    public HttpResultDTO findById(String tableId) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO customized(@Valid CreateCustomTableDTO dto) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

}
