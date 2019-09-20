package com.ydhl.micro.client.admin.service.weixin;

import com.ydhl.micro.api.enpoint.admin.weixin.WeiXinApi;
import com.ydhl.micro.client.admin.service.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName LoginFeignClient
 * @Description 微信接口
 * @Author yangll
 * @Date 2019-9-20 13:24:12
 * @Version 1.0
 **/
@FeignClient(value = "micro-base", configuration = FeignInterceptor.class, fallback = WeiXinApiClientFallback.class)
public interface WeiXinApiClient extends WeiXinApi {

}
