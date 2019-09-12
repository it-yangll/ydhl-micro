package com.ydhl.micro.base.entity;

import lombok.Data;

/**
 * 表: sys_area_village
 */
@Data
public class SysAreaVillage {
    /**
     * 村级（村委会、居委会）编码
     *
     * 是否允许NULL值:  不允许
     */
    private String code;

    /**
     * 村级（村委会、居委会）名称
     *
     * 是否允许NULL值:  不允许
     */
    private String name;

    /**
     * 乡级（乡镇、街道）编码
     *
     * 是否允许NULL值:  不允许
     */
    private String streetCode;

    /**
     * 县级（区县）编码
     *
     * 是否允许NULL值:  不允许
     */
    private String districtCode;

    /**
     * 地级（城市）编码
     *
     * 是否允许NULL值:  不允许
     */
    private String cityCode;

    /**
     * 省级（省份、直辖市、自治区）编码
     *
     * 是否允许NULL值:  不允许
     */
    private String provinceCode;
}