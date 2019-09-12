package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName UserState
 * @Description 平台来源
 * @Author yangll
 * @Date 2019/3/28 9:38
 * @Version 1.0
 **/
public enum PlatformSource {

    /** 小程序 */
    APP("小程序"),

    /** 商城 */
    PC("商城");

    /** 获取描述名称 */
    PlatformSource(String showText) {
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
