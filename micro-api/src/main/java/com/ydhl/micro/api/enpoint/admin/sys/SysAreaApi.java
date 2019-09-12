package com.ydhl.micro.api.enpoint.admin.sys;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName SysAreaApi
 * @Description 地区
 * @Author Ly
 * @Date 2019/5/28 16:30
 * @Version 1.0
 **/
public interface SysAreaApi {

    /** 省份列表 */
    @RequestMapping(value = "area/provinces", method = RequestMethod.GET)
    HttpResultDTO findProvinces();

    /** 地级（城市）列表 */
    @RequestMapping(value = "area/citys", method = RequestMethod.GET)
    HttpResultDTO findCitys(@RequestParam(name = "provinceCode") String provinceCode);

    /** 县级（区县）列表 */
    @RequestMapping(value = "area/districts", method = RequestMethod.GET)
    HttpResultDTO findDistricts(@RequestParam(name = "cityCode") String cityCode);

    /** 乡级（乡镇、街道）列表 */
    @RequestMapping(value = "area/streets", method = RequestMethod.GET)
    HttpResultDTO findStreets(@RequestParam(name = "districtCode") String districtCode);

    /** 村级（村委会、居委会）列表 */
    @RequestMapping(value = "area/villages", method = RequestMethod.GET)
    HttpResultDTO findVillages(@RequestParam(name = "streetCode") String streetCode);

}
