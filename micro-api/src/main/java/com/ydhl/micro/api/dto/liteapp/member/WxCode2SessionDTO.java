package com.ydhl.micro.api.dto.liteapp.member;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName WxCode2SessionDTO
 * @Description 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
 * @Author Ly
 * @Date 2019/6/15 11:34
 * @Version 1.0
 **/
@Data
@ToString
public class WxCode2SessionDTO {

    /** 用户唯一标识 */
    private String openid;

    /** 会话密钥 */
    private String session_key;

    /** 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。 */
    private String unionid;

    /** 错误码 */
    private Integer errcode;

    /** 错误信息 */
    private String errmsg;
}
