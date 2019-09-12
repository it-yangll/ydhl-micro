package com.ydhl.micro.core.consts;

import cn.hutool.core.date.DateUtil;
import com.ydhl.micro.api.enumcode.consts.CaptchaMoudleEnum;
import com.ydhl.micro.api.enumcode.consts.LoginType;
import com.ydhl.micro.api.enumcode.consts.UserType;

import java.text.MessageFormat;
import java.util.Date;

/**
 * @ClassName RedisKeyEnum
 * @Description redis缓存key生成类
 * @Author yangll
 * @Date 2019-9-10 13:12:58
 * @Version 1.0
 **/
public class RedisKeyConst {

    /** 用户令牌缓存key，登录后生成 */
    private static final String USER_TOKEN_KEY = "sys:token:{0}:{1}:{2}";

    /** 微信登录凭证，小程序调用微信登录生成 */
    private static final String WX_SESSION_KEY = "sys:wxSession:{0}";

    /** 用户权限缓存key，登录后生成 */
    private static final String USER_PERMISSION_KEY = "sys:user:permission:{0}";

    /** 部门数据缓存key,部门管理增删改时维护 */
    public static final String DEPT_KEY = "sys:dept";

    /** 数据字典配置项缓存key,数据字典增删改时维护 */
    private static final String DICT_ITEM_ID_KEY = "sys:dict:item:id:{0}";

    /** 数据字典配置项缓存key,数据字典增删改时维护 */
    private static final String DICT_ITEM_CODE_KEY = "sys:dict:item:code:{0}";

    /** 数据字典配置项MAP缓存key,数据字典增删改时维护 */
    public static final String DICT_ITEM_CODE_MAP_KEY = "sys:dict:item:map:code";

    /** 数据字典配置项MAP缓存key,数据字典增删改时维护 */
    public static final String DICT_ITEM_ID_MAP_KEY = "sys:dict:item:map:id";

    /** 数据字典code缓存key,数据字典增删改时维护 */
    private static final String DICT_CODE_MAP_KEY = "sys:dict:map:code:{0}";

    /** 数据字典id缓存key,数据字典增删改时维护 */
    private static final String DICT_ID_MAP_KEY = "sys:dict:map:id:{0}";

    /** 角色缓存key,角色管理增删改时维护 */
    public static final String ROLE_KEY = "sys:role";

    /** 资源数据缓存key,资源管理增删改时维护 */
    public static final String RESOURCE_KEY = "sys:resource";

    /** 订单当前流水号 */
    private static final String OMS_ORDER_SEQUENCE_KEY = "sequence:order:{0}";

    /** 手机验证码 */
    private static final String MOBILE_CAPTCHA_KEY = "captcha:mobile:{0}:{1}";

    /** 消息模板缓存key,定时任务自动刷新消息模板表到缓存中 */
    public static final String MSG_TEMPLATE_MAP_KEY = "sys:template:msg";

    /** 手机号发送短信次数 */
    private static final String MOBILE_SMS_SEQUENCE_KEY = "sequence:sms:{0}:{1}";

    /** 商品类型缓存key,商品类型管理增删改时维护 */
    public static final String GOODS_CATEGORY_CODE_MAP_KEY = "pms:map:category:code";
    public static final String GOODS_CATEGORY_ID_MAP_KEY = "pms:map:category:id";

    /** 商品标签缓存key,商品类型管理增删改时维护 */
    public static final String GOODS_LABEL_CODE_MAP_KEY = "pms:map:label:code";
    public static final String GOODS_LABEL_ID_MAP_KEY = "pms:map:label:id";

    /** 下单防止重复提交验证id */
    private static final String OMS_ORDER_TRADE_ID_KEY = "oms:order:trade-{0}-{1}";

    /** 回收订单当前流水号 */
    private static final String REC_ORDER_SEQUENCE_KEY = "sequence:rec:{0}";

    /** 客服QQ在线状态缓存 */
    private static final String QQ_ONLINE_STATE_KEY = "mall:qq:{0}";

    /** 上次全量同步类特征日期 */
    public static final String LAST_FULL_SYNC_SAP_CLASS_KEY = "sys:sap:sync:class:lastFullSyncDate";

    /** 上次同步类特征日期 */
    public static final String LAST_SYNC_SAP_CLASS_KEY = "sys:sap:sync:class:lastSyncDate";

    /** 第三方调用接口令牌 */
    private static final String THIRD_TOKEN_KEY = "sys:third:token:{0}-{1}";

    public static final String getUserTokenKey(String userName, LoginType loginType, UserType userType) {
        return MessageFormat.format(USER_TOKEN_KEY, loginType, userType, userName);
    }

    public static final String getWxSessionKey(String openId) {
        return MessageFormat.format(WX_SESSION_KEY, openId);
    }

    public static final String getUserPermissionKey(String name) {
        return MessageFormat.format(USER_PERMISSION_KEY, name);
    }

    public static final String getDictItemIdKey(Long itemId) {
        return MessageFormat.format(DICT_ITEM_ID_KEY, itemId);
    }

    public static final String getDictItemCodeKey(String itemCode) {
        return MessageFormat.format(DICT_ITEM_CODE_KEY, itemCode);
    }

    public static final String getDictCodeMapKey(String typeCode) {
        return MessageFormat.format(DICT_CODE_MAP_KEY, typeCode);
    }

    public static final String getDictIdMapKey(String typeCode) {
        return MessageFormat.format(DICT_ID_MAP_KEY, typeCode);
    }

    public static final String getOmsOrderSequenceKey() {
        return MessageFormat.format(OMS_ORDER_SEQUENCE_KEY, DateUtil.format(new Date(), "yyyyMMdd"));
    }

    public static final String getMobileCaptchaKey(String phone, CaptchaMoudleEnum moudle) {
        return MessageFormat.format(MOBILE_CAPTCHA_KEY, moudle, phone);
    }

    public static final String getMobileSmsSequenceKey(String phone) {
        return MessageFormat.format(MOBILE_SMS_SEQUENCE_KEY, DateUtil.format(new Date(), "yyyyMMdd"), phone);
    }

    public static final String getOmsOrderTradeIdKey(Long memberId, Long goodsId) {
        return MessageFormat.format(OMS_ORDER_TRADE_ID_KEY, memberId, goodsId);
    }

    public static final String getRecOrderSequenceKey() {
        return MessageFormat.format(REC_ORDER_SEQUENCE_KEY, DateUtil.format(new Date(), "yyyyMMdd"));
    }

    public static final String getQqOnlineStateKey(String qq) {
        return MessageFormat.format(QQ_ONLINE_STATE_KEY, qq);
    }

    public static final String getThirdTokenKey(String appId, String appSecret) {
        return MessageFormat.format(THIRD_TOKEN_KEY, appId, appSecret);
    }

}
