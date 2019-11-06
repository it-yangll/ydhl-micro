package com.ydhl.micro.api.enumcode;

/**
 * @ClassName GlobalCodeEnum
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 11:47:31
 * @Version 1.0
 **/
public enum GlobalCodeEnum implements CodeEnumClass {

    OK("200", "成功"),

    ERR_COMMON("500", "请求处理失败"),

    ERR_UNAUTHORIZED("403", "请求未授权"),

    ERR_LOGIN("G003", "账号/密码错误"),

    ERR_UNLOGIN("G004", "请登录"),

    ERR_UNAUTHENTICATED("401", "请求未认证"),

    ERR_PARAM_BIND("G400", "请求参数不合法"),

    ERR_PARAM_NOTREADABLE("G400", "无法解析请求参数"),

    ERR_PARAM_MISSING("G400", "缺少{0}参数"),

    ERR_SERVER_UNAVAILABLE("G500", "服务不可用"),

    ERR_FEGIN("G510", "调用服务异常"),

    ERR_JWT_BUILD("G600", "令牌生成失败"),

    ERR_JWT_INVALID("G601", "令牌无效"),

    ERR_JWT_DISUSED("G602", "令牌失效"),

    ERR_JWT_PARSE("G603", "解析令牌失败"),

    ERR_UPLOAD_FILESIZE_MAX("G604", "上传错误：大小超过指定值。"),

    ERR_WX_CODE2SESSION("G605", "登录异常"),

    ERR_WX_ACCESS_TOKEN("G607", "微信认证异常"),

    ERR_WX_ACCESS_USERID_TOKEN("G608", "微信获取用户ID认证异常"),

    ERR_WX_ACCESS_USERID_CODE("G609", "微信获取用户CODE过期"),

    ERR_WX_ACCESS_USERID_USERID("G610", "当前用户未开启企业微信认证，请关注泰和诚企业二维码."),

    ERR_INCOMPLETE("G500", "数据不完整"),

    ERR_TYPE_MISMATCH("G500", "数据类型匹配错误"),

    ERR_SMS_SEND_FAIL("G606", "短信发送失败"),

    ERR_REPAIR_FAIL("G706", "获取信息失败"),

    ERR_DELETE_REFERENCE_DATA("G607", "【{0}】存在引用数据，禁止删除"),

    ERR_ORDER_REPEAT("G701", "订单重复提交"),

    ERR_ORDER_FAIL("G702", "提交订单失败"),

    ERR_SUBMIT_ORDER_EXCEPTION("G703", "提交订单异常"),

    ERR_ORDER_REPEAT_GOODS_ORDER("G704", "您已对此商品下过单，不要对同一商品重复下单"),

    ERR_CANCEL_ORDER_EXCEPTION("G705", "取消订单异常"),

    ERR_ORDER_CONFIRM_RECEIPT("G706", "确认收货异常"),

    ERR_ORDER_PAY_DEPOSIT("G707", "支付定金异常"),

    ERR_ORDER_PAY_DEFRAY("G708", "支付尾款异常"),

    ERR_ORDER_CONFIG("G709", "确认订单异常"),

    ERR_ORDER_REFUSE("G710", "拒绝订单异常"),

    ERR_ORDER_CLOSE("G711", "关闭订单异常"),

    ERR_ORDER_SEND_GOODS("G712", "发送货物异常"),

    ERR_ORDER_MODIFY_DELIVERY("G713", "修改配送方式异常"),

    ERR_OMSORDER_DETAIL("G714", "您没有此订单信息，请确认订单信息"),

    ERR_ORDER_SEND_GOODS_FAIL("G715", "发送货物失败"),

    ERR_CANCEL_ORDER_FAIL("G716", "取消订单失败"),

    ERR_ORDER_CONFIRM_FAIL("G717", "确认收货失败"),

    ERR_ORDER_PAY_DEPOSIT_FAIL("G718", "支付定金失败"),

    ERR_ORDER_PAY_DEFRAY_FAIL("G719", "支付尾款失败"),

    ERR_ORDER_CONFIG_FAIL("G720", "确认订单失败"),

    ERR_ORDER_REFUSE_FAIL("G721", "拒绝订单失败"),

    ERR_ORDER_CLOSE_FAIL("G722", "关闭订单失败"),

    ERR_ORDER_MODIFY_DELIVERY_FAIL("G723", "修改配送方式失败"),

    ERR_APPOINTMENT_EXIST_NO_HANDLE("G724", "您有未处理的预约单，请等待处理完毕后再次预约"),

    ERR_APPOINTMENT_FAIL("G725", "预约看车失败"),

    ERR_APPOINTMENT_NO_EXIST("G726", "预约看车单不存在"),

    ERR_APPOINTMENT_HANDLEED("G727", "预约看车单已经处理过了，请不要重复处理"),

    ERR_APPOINTMENT_HANDLE_FAIL("G727", "处理预约看车单失败"),

    ERR_ORDER_CONFIRM_ALREADY("G728", "已确认收货，请勿重复操作"),

    ERR_GOODS_NON_EXIST("G801", "商品不存在"),

    ERR_GOODS_SALED_OR_NON_SHELF("G802", "该商品未上架或已售出"),

    ERR_MEMBER_NON_EXIST("G901", "该会员不存在"),

    ERR_DATA_EXIST("G902", "记录已存在"),

    ERR_CONFIG("G903","配置项[{0}]配置错误,{1}"),

    ERR_FROZEN_ACCOUNT("-1","该账号已冻结，请联系管理员"),

    ERR_FROZEN_MEMBER("-1","抱歉，您的账号已被冻结，请联系客服解封"),

    ERR_RECORD_NOT_EXIST("-1","抱歉，数据不存在");

    private String code;
    private String msg;

    private GlobalCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String getEnumCode() {
        return code;
    }

    @Override
    public String getEnumMsg() {
        return msg;
    }}
