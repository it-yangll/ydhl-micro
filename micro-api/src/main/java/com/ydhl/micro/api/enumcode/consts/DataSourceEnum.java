package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName DataSourceEnum
 * @Description 数据来源
 * @Author yangll
 * @Date 2019-9-10 11:48:06
 * @Version 1.0
 **/
public enum DataSourceEnum {

    /** 本地创建 */
    LOCAL("本地创建"),

    /** SAP同步 */
    SAP("SAP同步");

    DataSourceEnum(String showText) {
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
