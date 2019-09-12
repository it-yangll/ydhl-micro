package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName OrderType
 * @Description 订单类型
 * @Author zhengew
 * @Date 2019-5-9 15:59:48
 * @Version 1.0
 **/
public enum OrderType {

    /** 普通订单 */
    GENERAL("普通订单"),

    /** 促销订单 */
    PROMOTE("促销订单");

    OrderType(String showText) {
        this.showText = showText;
    }

    private String showText;

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }
}
