package com.ydhl.micro.core.security;


import com.ydhl.micro.api.enumcode.consts.LoginType;
import com.ydhl.micro.api.enumcode.consts.UserType;

import java.util.Date;

/**
 * @ClassName JwtBody
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 13:50:31
 * @Version 1.0
 **/
public class JwtBody {

    //***********官方默认载荷************
    /**
     * 编号
     **/
    private String jwtId;

    /**
     * 签发时间
     **/
    private Date issuedAt;

    /**
     * 过期时间
     **/
    private long expiresAt;
    //***********官方默认载荷************


    //************自定义载荷 AES加密*************
    /**
     * 来源IP
     **/
    private String ip;
    /**
     * 登录类型
     **/
    private LoginType loginType;
    /**
     * 登录类型(会员 or 系统用户)
     **/
    private UserType userType;
    /**
     * 身份标识
     **/
    private String identity;
    /**
     * 主键id
     **/
    private Long pk;
    /**
     * 姓名
     **/
    private String name;
    //************自定义载荷 AES加密*************


    public static JwtBody createJwtBody(String jwtId, long expiresAt, String ip, LoginType loginType, UserType userType, String identity, Long pk, String name) {
        JwtBody body = new JwtBody();
        body.setJwtId(jwtId);
        body.setExpiresAt(expiresAt);
        body.setIp(ip);
        body.setLoginType(loginType);
        body.setUserType(userType);
        body.setIdentity(identity);
        body.setPk(pk);
        body.setName(name);
        return body;
    }

    public String getJwtId() {
        return jwtId;
    }

    public void setJwtId(String jwtId) {
        this.jwtId = jwtId;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JwtBody{" +
                "jwtId='" + jwtId + '\'' +
                ", issuedAt=" + issuedAt +
                ", expiresAt=" + expiresAt +
                ", ip='" + ip + '\'' +
                ", loginType=" + loginType +
                ", userType=" + userType +
                ", identity='" + identity + '\'' +
                ", pk=" + pk +
                ", name='" + name + '\'' +
                '}';
    }
}
