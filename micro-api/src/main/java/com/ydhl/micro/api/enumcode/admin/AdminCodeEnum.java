package com.ydhl.micro.api.enumcode.admin;

import com.ydhl.micro.api.enumcode.CodeEnumClass;

/**
 * @ClassName GlobalCodeEnum
 * @Description TODO
 * @Autho yangll
 * @Date 2019-9-10 11:49:35
 * @Version 1.0
 **/
public enum AdminCodeEnum implements CodeEnumClass {

    ERR_DEL_ADMIN_USER("A001", "禁止操作管理员账号"),
    ERR_UPDATE_PWD("A002", "确认密码和新密码不一致"),
    ERR_OLD_PWD("A003", "原密码错误"),
    ERR_EDIT_PRESET("A004", "禁止操作内置数据"),
    ERR_ROLE_REPEAT("A005", "角色编码重复"),
    ERR_USER_REPEAT("A006", "用户已存在"),
    ERR_RESOURCE_CHILD("A007", "存在子资源，不允许删除"),
    ERR_DICITEM_REPEAT("A008", "数据字典配置项重复"),
    ERR_FACTOR_TYPE_REPEAT("A09", "评估因素类型重复"),
    ERR_FACTOR_ITEM_REPEAT("A010", "评估因素配置项重复"),
    ERR_MEMBER_REPEAT("A011", "会员已存在,手机号唯一"),
    ERR_DEL_FROZEN_MEMBER("A012", "禁止操作冻结会员账号"),
    ERR_FROZEN_STATE_INFO("A013", "只允许操作冻结用户"),
    ERR_USER_NAME_REPEAT("A0014", "用户名称已存在"),
    ERR_MALL_FROZEN_REPEAT("M001", "只允许操作正常会员"),
    ERR_MALL_NORMAL_REPEAT("M002", "只允许操作冻结会员"),

    ERR_UPDATE_STATE("G001", "商品已上架且已出售不允许此操作."),
    ERR_UPPER_UPDATE_STATE("G002", "促销商品已上架或已促销不允许此操作."),
    ERR_LOWER_UPDATE_STATE("G003", "促销商品已下架不允许此操作."),
    ERR_LOWER_LOWERSHELF("G031", "该商品已是下架商品不允许此操作."),
    ERR_LOWER_UPPERSHELF("G032", "该商品已是上架商品不允许此操作."),
    ERR_DEL_UPDATE("G004", "促销商品已上架不允许删除."),
    ERR_UPDATE_PROMOTION_GOODS("G041", "只允许操作未促销商品."),
    ERR_UPDATE_ADD_PROMOTION_GOODS("G042", "商品未上架不可参加促销."),
    ERR_DEL_BANNER_UPDATE("G0043", "轮播图已上线不允许删除."),
    ERR_GOODS_SMS_UPDATE_STATE("G005", "下架商品中包含促销商品不允许此操作."),
    ERR_BANNER_UPP_UPDATE_STATE("G006", "轮播图片已上线不允许此操作."),
    ERR_BANNER_LOW_UPDATE_STATE("G007", "轮播图片已下线不允许此操作."),
    ERR_GOODS_UPDATE_STATE("G008", "商品已在促销不允许此操作."),
    ERR_GOODS_SMSGOODS_DELETE_STATE("G008", "此商品正在参加促销不允许删除."),
    ERR_GOODS_PROMOTIONPRICE_STATE("G009", "促销商品必须添加促销价格."),
    ERR_GOODS_PRICE_STATE("G010", "促销价格不可大于商品价格."),
    ERR_GOODS_CAR_STATE("G0011", "该商品已存在不可重复添加."),
    ERR_VIN_STATE("REC1001", "车架号不存在"),
    ERR_VIN_STATE2("REC1002", "车架号已存在"),
    ERR_PRODUCT_WARNING("REC1003", "产品代码已存在"),
    ERR_CHARACTER_WARNING("REC1004", "特性代码已存在"),
    ERR_FEATURE_WARNING("REC1005", "特征值代码已存在"),
    ERR_VIN_PARAM_STATE("REC1006", "车架号没有完车信息参数"),
    ERR_MATCH_PARAM_STATE("REC1007", "评估规则匹配项为空"),
    ERR_MOBILE_PARAM_STATE("REC1008", "手机号没有评估过"),
    ERR_FACTOR_ITEMS("REC1009", "自定义匹配项错误"),
    ERR_CLASS_ITEMS("REC1010", "特性或者类不存在"),
    ERR_FEATURE_ITEMS("REC1011", "特征不存在"),
    ERR_RULE_ADD_ERROR("REC1012", "规则名称或代码已存在"),
    ERR_GOODS_CAR_FIND("REC1013", "车架号为空.请重新键入"),
    ERR_GATEGORY_NOT_UPDATE("REC1014", "商品类型编码不可修改."),
    ERR_MATCH_WARNING("REC1015", "匹配项不能为空."),
    ERR_PRICE_WARNING("REC1017", "输入价格不在可选范围区间."),
    ERR_CLASSCODE_WARNING("REC1015", "类代码已存在"),
    ERR_FEATURE_CODE_WARNING("REC1016", "特征代码已存在"),
    ERR_DICITEM_WARNING("REC1017", "技术参数已存在"),
    ERR_FRONTRULE_WARNING("REC1018", "前置规则不能添加规则本身"),
    ERR_ZHIZH_WARNING("REC1019", "参数值不包含数字，无法参与计算"),
    ERR_RULES_WARNING("REC1020", "规则不存在"),
    ERR_TECHNICAL_WARNING("REC1021", "该车架号技术参数不存在"),
    ERR_CLASSDEL_WARNING("REC1022", "所选类代码不存在，无法进行评估"),
    ERR_APPSTATE_WARNING("REC1023", "只能选择评估中的订单"),
    ERR_APPSELLSTATE_WARNING("REC1024", "所选订单不在卖车范围"),
    ERR_APPSTOPSTATE_WARNING("REC1025", "所选订单不在拒绝范围"),
    ERR_APPVINSTATE_WARNING("REC1026", "该车架号已评估完成，不可再次提交"),
    ERR_DICITEME_WARNING("REC1027", "运输方式不在数据字典中"),
    ERR_SERVICEORDER_WARNING("REC1028", "服务订单不存在"),
    ERR_RULESCAU_WARNING("REC1029", "不参与计算不可以设置匹配规则"),
    ERR_GATEGORY_NOT_CODE("G018", "商品类型编码已存在."),
    ERR_LABEL_NOT_UPDATE("G019", "商品标签编码不可修改."),
    ERR_LABEL_NOT_CODE("G020", "商品标签编码已存在."),
    ERR_GOODS_SELL_CAR("G021", "已出售商品不可修改.");

    private String code;
    private String msg;

    private AdminCodeEnum(String code, String msg) {
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
