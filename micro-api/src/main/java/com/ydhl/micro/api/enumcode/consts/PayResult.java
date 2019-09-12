package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName PayResult
 * @Description 支付结果
 * @Author zhengew
 * @Date 2019-5-13 14:49:37
 * @Version 1.0
 **/
public enum PayResult {

    /** 待支付 */
    PAIED("待支付"),

    /** 支付成功 */
    SUCCESS("支付成功"),

    /** 支付失败 */
    FAILED("支付失败");

    PayResult(String showText) {
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
