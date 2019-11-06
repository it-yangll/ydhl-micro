package com.ydhl.micro.base.controller.weixin;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.weixin.WeiXinApi;
import com.ydhl.micro.base.weixin.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName AuthenticationController
 * @Description TODO
 * @Author yanll
 * @Date 2019-9-20 13:25:41
 * @Version 1.0
 **/
@RestController
public class WXController implements WeiXinApi {

    @Autowired
    private WeChatService weChatService;

    @Override
    public HttpResultDTO getAccountCode() { return HttpResultDTO.ok(weChatService.redirectWXAuthUrl()); }

    @Override
    public HttpResultDTO getWXUserInfo(String code, String state) { return HttpResultDTO.ok(weChatService.getWXUserInfo(code,state)); }


}
