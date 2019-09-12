package com.ydhl.micro.base.entity;

import lombok.Data;

/**
 * 表: sys_area_district
 */
@Data
public class SysAreaDistrict {
    /**
     * 县级（区县）编码
     *
     * 是否允许NULL值:  不允许
     */
    private String code;

    /**
     * 县级（区县）名称
     *
     * 是否允许NULL值:  允许
     */
    private String name;

    /**
     * 地级（城市）编码
     *
     * 是否允许NULL值:  允许
     */
    private String cityCode;

    /**
     * 省级（省份、直辖市、自治区）编码
     *
     * 是否允许NULL值:  允许
     */
    private String provinceCode;
}