package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.enpoint.admin.sys.SysLogApi;
import com.ydhl.micro.api.enpoint.admin.sys.SysPostApi;
import com.ydhl.micro.client.admin.service.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SysRoleFeignClient
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 15:10
 * @Version 1.0
 **/
@FeignClient(value = "micro-base", configuration = FeignInterceptor.class, fallback = SysPostFeignClientFallback.class)
public interface SysPostFeignClient extends SysPostApi {
}
