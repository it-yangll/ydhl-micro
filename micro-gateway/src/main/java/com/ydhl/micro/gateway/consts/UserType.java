package com.ydhl.micro.gateway.consts;

/**
 * @ClassName UserType
 * @Description 用户类型
 * @Author yangll
 * @Date 2019-9-10 11:38:49
 * @Version 1.0
 **/
public enum UserType {

    /** 员工用户 */
    EMPLOYEE("员工用户"),

    /** 网点用户 */
    SALESMAN("网点用户"),

    /** 管理员用户 */
    ADMIN("管理员用户"),

    /** 会员用户 */
    MEMBER("会员用户");

    private String showText;

    UserType(String showText) {
        this.showText = showText;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

}
