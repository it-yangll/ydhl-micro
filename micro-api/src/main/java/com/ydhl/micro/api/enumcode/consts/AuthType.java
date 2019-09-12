package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName UserType
 * @Description 授权类型
 * @Author yangll
 * @Date 2019-9-10 11:49:07
 * @Version 1.0
 **/
public enum AuthType {

    /** 用户 */
    USER("用户"),

    /** 部门 */
    DEPT("部门"),

    /** 职务 */
    POST("职务");

    AuthType(String showText) {
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
