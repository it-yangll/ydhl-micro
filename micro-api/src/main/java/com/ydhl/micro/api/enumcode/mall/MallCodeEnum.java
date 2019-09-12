package com.ydhl.micro.api.enumcode.mall;

import com.ydhl.micro.api.enumcode.CodeEnumClass;

/**
 * @ClassName GlobalCodeEnum
 * @Description TODO
 * @Author Ly
 * @Date 2019/3/25 12:15
 * @Version 1.0
 **/
public enum MallCodeEnum implements CodeEnumClass {

    ERR_UPDATE_PWD("M001", "确认密码和新密码不一致"),
    ERR_OLD_PWD("M002", "原密码错误"),
    ERR_MEMBER_REPEAT("M003", "账号已存在"),
    ERR_MOBILE_REPEAT("M004", "该手机号已注册"),
    ERR_CAPTCHA("M005", "验证码无效"),
    ERR_MEMBER_EMPTY("M006", "账号不存在"),
    ERR_MEMBER_USER_NAME_FORMAT("M007", "登录账号不能为纯数字");

    private String code;
    private String msg;

    private MallCodeEnum(String code, String msg) {
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
