package com.ydhl.micro.api.enpoint.admin.weixin;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    HttpResultDTO gtewxtk();


    /** 获取用户权限 */
    @RequestMapping(value = "redirectUriCode", method = RequestMethod.GET)
    HttpResultDTO redirectUriCode(String code);



}
