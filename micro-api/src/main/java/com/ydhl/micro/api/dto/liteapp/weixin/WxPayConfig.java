package com.ydhl.micro.api.dto.liteapp.weixin;

/**
 * @ClassName WxPayConfig
 * @Description 微信支付参数配置
 * @Author: zhengew
 * @Date: 2019-6-20 19:41:21
 * @Version: 1.0
 */
public class WxPayConfig {
    //小程序appid
    public static final String appid = "";
    //微信支付的商户id
    public static final String mch_id = "";
    //微信支付的商户密钥
    public static final String key = "";
    //支付成功后的服务器回调url
    public static final String notify_url = "https://??/weixin/wxNotify";
    //签名方式
    public static final String SIGNTYPE = "MD5";
    //交易类型
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
