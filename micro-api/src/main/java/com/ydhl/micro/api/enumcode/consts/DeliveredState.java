package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName DeliveredState
 * @Description 交付状态
 * @Author zhengew
 * @Date 2019-5-14 10:28:30
 * @Version 1.0
 **/
public enum DeliveredState {

    /** 未交付 */
    UNDELIVERED("未交付"),

    /** 已交付 */
    DELIVERED("已交付");

    DeliveredState(String showText) {
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
