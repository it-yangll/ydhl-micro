package com.ydhl.micro.base.controller.sys;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.sys.SysAreaApi;
import com.ydhl.micro.base.dao.auto.*;
import com.ydhl.micro.base.entity.SysAreaCityExample;
import com.ydhl.micro.base.entity.SysAreaDistrictExample;
import com.ydhl.micro.base.entity.SysAreaStreetExample;
import com.ydhl.micro.base.entity.SysAreaVillageExample;
import com.ydhl.micro.base.dao.auto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysAreaController
 * @Description TODO
 * @Author Ly
 * @Date 2019/3/23 17:59
 * @Version 1.0
 **/
@RestController
@Slf4j
public class SysAreaController implements SysAreaApi {

    @Autowired
    private SysAreaProvinceMapper provinceMapper;

    @Autowired
    private SysAreaCityMapper cityMapper;

    @Autowired
    private SysAreaDistrictMapper districtMapper;

    @Autowired
    private SysAreaStreetMapper streetMapper;

    @Autowired
    private SysAreaVillageMapper villageMapper;

    @Override
    public HttpResultDTO findProvinces() {
        return HttpResultDTO.ok(provinceMapper.selectByExample(null));
    }

    @Override
    public HttpResultDTO findCitys(String provinceCode) {
        SysAreaCityExample exp = new SysAreaCityExample();
        exp.createCriteria().andProvinceCodeEqualTo(provinceCode);
        return HttpResultDTO.ok(cityMapper.selectByExample(exp));
    }

    @Override
    public HttpResultDTO findDistricts(String cityCode) {
        SysAreaDistrictExample exp = new SysAreaDistrictExample();
        exp.createCriteria().andCityCodeEqualTo(cityCode);
        return HttpResultDTO.ok(districtMapper.selectByExample(exp));
    }

    @Override
    public HttpResultDTO findStreets(String districtCode) {
        SysAreaStreetExample exp = new SysAreaStreetExample();
        exp.createCriteria().andDistrictCodeEqualTo(districtCode);
        return HttpResultDTO.ok(streetMapper.selectByExample(exp));
    }

    @Override
    public HttpResultDTO findVillages(String streetCode) {
        SysAreaVillageExample exp = new SysAreaVillageExample();
        exp.createCriteria().andStreetCodeEqualTo(streetCode);
        return HttpResultDTO.ok(villageMapper.selectByExample(exp));
    }
}
