package com.ydhl.micro.client.admin.controller.sys;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.sys.SysAreaFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysAreaClient
 * @Description 地区管理
 * @Author yangll
 * @Date 2019-9-11 11:39:27
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "地区管理相关接口")
public class SysAreaClient {

    @Autowired
    private SysAreaFeignClient areaFeignClient;

    /** 省份列表 */
    @ApiOperation(value = "省份列表")
    @RequestMapping(value = "area/provinces", method = RequestMethod.GET)
    HttpResultDTO findProvinces() {
        return areaFeignClient.findProvinces();
    }

    /** 地级（城市）列表 */
    @ApiOperation(value = "地级（城市）列表")
    @RequestMapping(value = "area/citys", method = RequestMethod.GET)
    HttpResultDTO findCitys(@ApiParam(value = "省份编码", required = true) @RequestParam(name = "provinceCode") String provinceCode) {
        return areaFeignClient.findCitys(provinceCode);
    }

    /** 县级（区县）列表 */
    @ApiOperation(value = "县级（区县）列表")
    @RequestMapping(value = "area/districts", method = RequestMethod.GET)
    HttpResultDTO findDistricts(@ApiParam(value = "地级编码", required = true) @RequestParam(name = "cityCode") String cityCode) {
        return areaFeignClient.findDistricts(cityCode);
    }

    /** 乡级（乡镇、街道）列表 */
    @ApiOperation(value = "乡级（乡镇、街道）列表")
    @RequestMapping(value = "area/streets", method = RequestMethod.GET)
    HttpResultDTO findStreets(@ApiParam(value = "县级编码", required = true) @RequestParam(name = "districtCode") String districtCode) {
        return areaFeignClient.findStreets(districtCode);
    }

    /** 村级（村委会、居委会）列表 */
    @ApiOperation(value = "村级（村委会、居委会）列表")
    @RequestMapping(value = "area/villages", method = RequestMethod.GET)
    HttpResultDTO findVillages(@ApiParam(value = "乡级编码", required = true) @RequestParam(name = "streetCode") String streetCode) {
        return areaFeignClient.findVillages(streetCode);
    }

}
