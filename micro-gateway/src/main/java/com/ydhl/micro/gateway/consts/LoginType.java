package com.ydhl.micro.gateway.consts;

/**
 * @ClassName LoginType
 * @Description 登录方式
 * @Author yangll
 * @Date 2019-9-10 11:37:59
 * @Version 1.0
 **/
public enum LoginType {

    /** 商城登录 */
    MALL("商城登录"),

    /** 运营平台登录 */
    ADMIN("运营平台登录"),

    /** 小程序登录 */
    LITEAPP("小程序登录");

    private String showText;

    LoginType(String showText) {
        this.showText = showText;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

}
