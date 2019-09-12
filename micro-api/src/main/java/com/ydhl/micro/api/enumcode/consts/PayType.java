package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName PayType
 * @Description 支付方式
 * @Author zhengew
 * @Date 2019-5-13 14:46:19
 * @Version 1.0
 **/
public enum PayType {

    /** 支付定金 */
    EARNEST("支付定金"),

    /** 支付尾款 */
    TAIL("支付尾款"),

    /** 全款支付 */
    ALL("全款支付");

    PayType(String showText) {
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
