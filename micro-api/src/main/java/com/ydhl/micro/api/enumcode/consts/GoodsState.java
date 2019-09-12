package com.ydhl.micro.api.enumcode.consts;

/**
 *商品状态码
 * 0:未上架
 * 1：已上架
 * 2:已购买
 * 3：已售出
 *@Author: yangll
 *@Date: 2019/5/5 10:22
 *@Version: 1.0
*/
public enum GoodsState {

    /** 未上架 */
    ZERO("0"),

    /** 已上架 */
    ONE("1"),

    /** 已购买 */
    TWO("2"),

    /** 已售出 */
    THREE("3");

    private String showText;

    GoodsState(String showText) {
        this.showText = showText;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }
}
