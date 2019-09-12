package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.dto.admin.sys.log.SearchLogDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @ClassName SysLogFeignClientFallback
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 16:14
 * @Version 1.0
 **/
@Component
public class SysLogFeignClientFallback implements SysLogFeignClient {

    @Override
    public HttpPageResultDTO pageSearch(SearchLogDTO searchDTO) {
        return HttpPageResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }
}
