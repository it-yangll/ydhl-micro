package com.ydhl.micro.base.weixin;

import com.ydhl.micro.api.dto.admin.sys.login.ResponseLoginDTO;
import com.ydhl.micro.api.dto.liteapp.member.ResponseWxSessionDTO;
import com.ydhl.micro.base.weixin.dto.BindWxDTO;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;


/**
 * @ClassName WeChatService
 * @Description weixin
 * @Author yangll
 * @Date 2019/9/18 0018 17:08
 * @Version 1.0
 **/
public interface WeChatService {

    /** 后台获取微信openId绑定账号 */
    ResponseWxSessionDTO bindWx(BindWxDTO dto);

    /** 获取微信用户code 5分钟内有效 */
    ResponseEntity<String> getAccountCode() throws UnsupportedEncodingException;

    /** 获取网页重定向路径 */
    String redirectWXAuthUrl();

    /** 获取微信用户ID信息 @param code @param state @return */
    ResponseLoginDTO getWXUserInfo(String code, String state);


}
