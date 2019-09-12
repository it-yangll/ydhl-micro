package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.enpoint.admin.sys.SysUserApi;
import com.ydhl.micro.client.admin.service.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SysUserFeignClient
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 13:07
 * @Version 1.0
 **/
@FeignClient(value = "micro-base", configuration = FeignInterceptor.class, fallback = SysUserFeignClientFallback.class)
public interface SysUserFeignClient extends SysUserApi {
}
