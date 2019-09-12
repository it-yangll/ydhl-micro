package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName ElemType
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 11:48:57
 * @Version 1.0
 **/
public enum ElemType {

    /** 单选框 */
    RADIO("单选框"),

    /** 复选框 */
    CHECKBOX("复选框"),

    INPUT("输入框"),

    /** 下拉框 */
    SELECT("下拉框");




    ElemType(String showText) {
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
