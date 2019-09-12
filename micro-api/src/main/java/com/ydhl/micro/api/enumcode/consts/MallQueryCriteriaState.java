package com.ydhl.micro.api.enumcode.consts;

/**
 *Mall固化查询条件
 * 0:未上架
 * 1：已上架
 * 2:已购买
 * 3：已售出
 * PC:PC 商城
 *@Author: yangll
 *@Date: 2019/5/5 10:22
 *@Version: 1.0
*/
public enum MallQueryCriteriaState {

    /** 未上架 */
    ZERO("0"),

    /** 已上架 */
    ONE("1"),

    /** 已购买 */
    TWO("2"),

    /** 已售出 */
    THREE("3"),

    PC("PC商城首页banner");

    private String showText;

    MallQueryCriteriaState(String showText) {
        this.showText = showText;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }
}
