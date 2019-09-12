package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName SourceType
 * @Description 订单来源
 * @Author zhengew
 * @Date 2019-5-9 15:57:37
 * @Version 1.0
 **/
public enum SourceType {

    /** 商城 */
    PC("商城"),

    /** 小程序 */
    APP("小程序");

    SourceType(String showText) {
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
