package com.ydhl.micro.client.admin.service.sys;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @ClassName SysAreaFeignClientFallback
 * @Description 地区联动
 * @Author Ly
 * @Date 2019/4/25 11:37
 * @Version 1.0
 **/
@Component
public class SysAreaFeignClientFallback implements SysAreaFeignClient {

    @Override
    public HttpResultDTO findProvinces() {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO findCitys(String provinceCode) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO findDistricts(String cityCode) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO findStreets(String districtCode) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }

    @Override
    public HttpResultDTO findVillages(String streetCode) {
        return HttpResultDTO.fail(GlobalCodeEnum.ERR_SERVER_UNAVAILABLE);
    }
}
