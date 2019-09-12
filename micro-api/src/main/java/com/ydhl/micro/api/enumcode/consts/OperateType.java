package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName OperateType
 * @Description 操作类型
 * @Author zhengew
 * @Date 2019-5-10 15:10:29
 * @Version 1.0
 **/
public enum OperateType {

    /** 用户 */
    CUSTOMER("用户"),

    /** 系统 */
    SYSTEM("系统"),

    /** 运营人员 */
    OPERATOR("运营人员");

    OperateType(String showText) {
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
