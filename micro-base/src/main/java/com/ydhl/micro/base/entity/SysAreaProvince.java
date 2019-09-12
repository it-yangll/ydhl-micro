package com.ydhl.micro.base.entity;

import lombok.Data;

/**
 * 表: sys_area_province
 */
@Data
public class SysAreaProvince {
    /**
     * 省级（省份、直辖市、自治区）编码
     *
     * 是否允许NULL值:  不允许
     */
    private String code;

    /**
     * 省级（省份、直辖市、自治区）名称
     *
     * 是否允许NULL值:  不允许
     */
    private String name;
}