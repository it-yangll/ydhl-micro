package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName PayChannel
 * @Description 支付渠道
 * @Author zhengew
 * @Date 2019-6-20 16:16:40
 * @Version 1.0
 **/
public enum PayChannel {

    /** 线上 */
    ONLINE("线上"),

    /** 线下 */
    OFFLINE("线下");

    PayChannel(String showText) {
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
