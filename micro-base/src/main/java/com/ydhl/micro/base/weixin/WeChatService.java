package com.ydhl.micro.base.weixin;

import com.ydhl.micro.api.dto.admin.sys.login.ResponseLoginDTO;
import com.ydhl.micro.api.dto.liteapp.member.ResponseWxSessionDTO;
import com.ydhl.micro.api.dto.mall.member.CaptchaDTO;
import com.ydhl.micro.api.dto.mall.member.LoginMallDTO;
import com.ydhl.micro.base.weixin.dto.BindWxDTO;
import com.ydhl.micro.base.weixin.dto.WxLoginAppDTO;

/**
 * @ClassName WeChatService
 * @Description weixin
 * @Author yangll
 * @Date 2019/9/18 0018 17:08
 * @Version 1.0
 **/
public interface WeChatService {

    /** 账号密码登录 */
    //ResponseLoginDTO login(LoginMallDTO dto);

    /** 微信授权手机号登录 */
    //ResponseLoginDTO wxLogin(WxLoginAppDTO dto);

    /** 后台获取微信openId绑定账号 */
    ResponseWxSessionDTO bindWx(BindWxDTO dto);

    /** 驾驶舱发送验证码 */
    void captcha(CaptchaDTO dto);


}
