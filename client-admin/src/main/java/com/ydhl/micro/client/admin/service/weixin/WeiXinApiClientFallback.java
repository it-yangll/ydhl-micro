package com.ydhl.micro.client.admin.service.weixin;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

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
    public HttpResultDTO getAccountCode() {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO getWXUserInfo(@RequestParam("code") final String code, @RequestParam("state") final String state) { return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE); }
}
