package com.ydhl.micro.base.entity;

import lombok.Data;

/**
 * 表: sys_area_city
 */
@Data
public class SysAreaCity {
    /**
     * 地级（城市）编码
     *
     * 是否允许NULL值:  不允许
     */
    private String code;

    /**
     * 地级（城市）名称
     *
     * 是否允许NULL值:  不允许
     */
    private String name;

    /**
     * 省级（省份、直辖市、自治区）
     *
     * 是否允许NULL值:  不允许
     */
    private String provinceCode;
}