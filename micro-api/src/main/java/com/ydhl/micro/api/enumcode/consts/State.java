package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName UserState
 * @Description 用户状态
 * @Author yangll
 * @Date 2019-9-10 11:49:21
 * @Version 1.0
 **/
public enum State {

    /** 正常 */
    NORMAL("正常"),

    /** 冻结 */
    FROZEN("冻结"),

    /** 删除 */
    DELETED("删除");

    State(String showText) {
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
