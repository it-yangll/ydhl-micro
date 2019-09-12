package com.ydhl.micro.api.enumcode.consts;

/**
 * @ClassName UserType
 * @Description 资源类型
 * @Author yangll
 * @Date 2019-9-10 11:48:19
 * @Version 1.0
 **/
public enum ResourceType {

    /** 目录 */
    FOLDER("目录"),

    /** 菜单 */
    MENU("菜单"),

    /** 按钮 */
    BUTTON("按钮");

    private String showText;

    ResourceType(String showText) {
        this.showText = showText;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

}
