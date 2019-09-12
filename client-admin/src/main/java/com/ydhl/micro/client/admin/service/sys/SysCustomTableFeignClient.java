package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.enpoint.admin.sys.SysCustomTableApi;
import com.ydhl.micro.client.admin.service.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName SysCustomTableFeignClient
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 17:20
 * @Version 1.0
 **/
@FeignClient(value = "micro-base", configuration = FeignInterceptor.class, fallback = SysCustomTableFeignClientFallback.class)
public interface SysCustomTableFeignClient extends SysCustomTableApi {
}
