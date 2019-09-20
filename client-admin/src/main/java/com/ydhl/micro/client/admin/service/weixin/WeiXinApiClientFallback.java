package com.ydhl.micro.client.admin.service.weixin;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @ClassName SysAreaFeignClientFallback
 * @Description 微信接口
 * @Author Ly
 * @Date 2019/4/25 11:37
 * @Version 1.0
 **/
@Component
public class WeiXinApiClientFallback implements WeiXinApiClient {

    @Override
    public HttpResultDTO gtewxtk() {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }
}
