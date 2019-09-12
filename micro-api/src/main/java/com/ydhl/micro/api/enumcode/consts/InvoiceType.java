package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName SourceType
 * @Description 发票类型
 * @Author zhengew
 * @Date 2019-5-10 11:57:19
 * @Version 1.0
 **/
public enum InvoiceType {

    /** 不开发票 */
    NOINVOICE("不开发票"),

    /** 电子发票 */
    ELECTRONICINVOICE("电子发票"),

    /** 纸质发票 */
    PAPERINVOICE("纸质发票");

    InvoiceType(String showText) {
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
