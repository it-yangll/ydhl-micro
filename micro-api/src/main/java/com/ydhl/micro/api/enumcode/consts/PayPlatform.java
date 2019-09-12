package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName PayPlatform
 * @Description 支付平台
 * @Author zhengew
 * @Date 2019-6-21 10:24:26
 * @Version 1.0
 **/
public enum PayPlatform {

    /** 微信 */
    WECHAT("微信"),

    /** 支付宝 */
    ALIPAY("支付宝");

    PayPlatform(String showText) {
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
