package com.ydhl.micro.base.controller.weixin;

import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.api.enpoint.admin.weixin.WeiXinApi;
import com.ydhl.micro.base.weixin.WeChatService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WXController implements WeiXinApi {

    @Autowired
    private WeChatService weChatService;

    @Override
    public HttpResultDTO gtewxtk() {
        return HttpResultDTO.ok(weChatService.getAccountToken());
    }


}
