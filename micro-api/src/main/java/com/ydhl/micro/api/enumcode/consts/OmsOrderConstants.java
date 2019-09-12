package com.ydhl.micro.api.enumcode.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OmsOrderConstants
 * @Description 订单管理常量类
 * @Author zhengew
 * @Date 2019-5-10 14:32:18
 * @Version 1.0
 **/
public class OmsOrderConstants {

    /**
     * 发票类型常量
     */
    public static final Map<Integer,String> INVOICE_TYPE_MAP = new HashMap<Integer,String>();
    /**
     * 操作类型常量
     */
    public static final Map<Integer,String> OPERATE_TYPE_MAP = new HashMap<Integer,String>();
    /**
     * 支付方式常量
     */
    public static final Map<Integer,String> PAY_TYPE_MAP = new HashMap<Integer,String>();
    /**
     * 支付结果常量
     */
    public static final Map<Integer,String> PAY_RESULT_MAP = new HashMap<Integer,String>();
    /**
     * 交付状态常量
     */
    public static final Map<Integer,String> DELIVERED_STATE_MAP = new HashMap<Integer,String>();
    /**
     * 订单状态常量
     */
    public static final Map<String,String> ORDER_STATE_MAP = new HashMap<String,String>();

    static {
        INVOICE_TYPE_MAP.put(0,"NOINVOICE");
        INVOICE_TYPE_MAP.put(1,"ELECTRONICINVOICE");
        INVOICE_TYPE_MAP.put(2,"PAPERINVOICE");

        OPERATE_TYPE_MAP.put(1,"CUSTOMER");
        OPERATE_TYPE_MAP.put(2,"SYSTEM");
        OPERATE_TYPE_MAP.put(3,"OPERATOR");

        PAY_TYPE_MAP.put(1,"EARNEST");
        PAY_TYPE_MAP.put(2,"TAIL");
        PAY_TYPE_MAP.put(3,"ALL");

        PAY_RESULT_MAP.put(0,"PAIED");
        PAY_RESULT_MAP.put(1,"SUCCESS");
        PAY_RESULT_MAP.put(2,"FAILED");

        DELIVERED_STATE_MAP.put(0,"UNDELIVERED");
        DELIVERED_STATE_MAP.put(1,"DELIVERED");

        ORDER_STATE_MAP.put("0","待确认");
        ORDER_STATE_MAP.put("1","自提待确认");
        ORDER_STATE_MAP.put("2","待支付定金");
        ORDER_STATE_MAP.put("3","待支付尾款");
        ORDER_STATE_MAP.put("4","待发货");
        ORDER_STATE_MAP.put("5","已发货");
        ORDER_STATE_MAP.put("6","已完成");
        ORDER_STATE_MAP.put("7","定金支付超时");
        ORDER_STATE_MAP.put("8","尾款支付超时");
        ORDER_STATE_MAP.put("9","已关闭");
        ORDER_STATE_MAP.put("10","已取消");
        ORDER_STATE_MAP.put("11","已拒绝");
        ORDER_STATE_MAP.put("12","待自提");
    }

}
