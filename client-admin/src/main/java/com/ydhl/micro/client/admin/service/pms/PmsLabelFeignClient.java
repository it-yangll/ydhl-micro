package com.ydhl.micro.client.admin.service.pms;

import com.ydhl.micro.api.enpoint.admin.goods.PmsLabelApi;
import com.ydhl.micro.client.admin.service.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: yangll
 * @description
 * @Date: 2019/4/30
 * @Version 1.0
 */
@FeignClient(value = "micro-order", configuration = FeignInterceptor.class, fallback = PmsLabelFeignClientFallback.class)
public interface PmsLabelFeignClient extends PmsLabelApi {
}
