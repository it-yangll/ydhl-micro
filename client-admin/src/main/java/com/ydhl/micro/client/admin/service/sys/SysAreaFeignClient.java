package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.enpoint.admin.sys.SysAreaApi;
import com.ydhl.micro.client.admin.service.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName LoginFeignClient
 * @Description 地区联动
 * @Author Ly
 * @Date 2019/4/25 11:36
 * @Version 1.0
 **/
@FeignClient(value = "micro-base", configuration = FeignInterceptor.class, fallback = SysAreaFeignClientFallback.class)
public interface SysAreaFeignClient extends SysAreaApi {
}
