package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.enpoint.admin.sys.LoginApi;
import com.ydhl.micro.client.admin.service.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName LoginFeignClient
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 11:36
 * @Version 1.0
 **/
@FeignClient(value = "micro-base", configuration = FeignInterceptor.class, fallback = LoginFeignClientFallback.class)
public interface LoginFeignClient extends LoginApi {
}
