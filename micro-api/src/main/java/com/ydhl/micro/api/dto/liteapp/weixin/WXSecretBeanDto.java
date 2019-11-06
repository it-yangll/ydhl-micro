package com.ydhl.micro.api.dto.liteapp.weixin;


/**
 * @ClassName WXSecretDto
 * @Description TODO
 * @Author yangll
 * @Date 2019-11-4 11:23:48
 * @Version 1.0
 **/
public class WXSecretBeanDto {

    /** 缓存名称 */
    public static final String WX_CACHETOKEN_NAME = "WX_CACHETOKEN_NAME";

     /** 状态码 */
     private Integer errcode;

     /** 状态信息 */
     private String errmsg;

     /** 程序唯一Token */
     private String access_token;

     /** 有效时间 */
     private Long expires_in;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    /** verify wx Time */
    private Boolean verifyExpires_in(){
        return Long.compare(expires_in,System.currentTimeMillis()) > 0?true:false;
    }


}
