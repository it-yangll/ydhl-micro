package com.ydhl.micro.api.enpoint.admin.weixin;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName AuthenticationApi
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-20 13:15:18
 * @Version 1.0
 **/
public interface WeiXinApi {

    /** 获取微信Token */
    @RequestMapping(value = "gtewxtk", method = RequestMethod.GET)
    HttpResultDTO getAccountCode() throws UnsupportedEncodingException;

    /** 获取微信用户Id */
    @RequestMapping(value = "getWXUserInfo", method = RequestMethod.GET)
    HttpResultDTO getWXUserInfo(@RequestParam("code") final String code, @RequestParam("state") final String state);



}
