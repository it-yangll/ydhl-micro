package com.ydhl.micro.client.admin.service.weixin.util;

import com.ydhl.micro.api.dto.admin.sys.login.ResponseLoginDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.weixin.WeiXinApiClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;


/**
 * @ClassName AuthenticationController
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-20 13:22:22
 * @Version 1.0
 **/
/*@RestController*/
@Slf4j
@Api(description = "微信基本接口")
public class WeiXinClientUtil {

    @Autowired
    private WeiXinApiClient weiXinApiClient;

    /**
     * @param  :
     * @return com.ydhl.micro.api.dto.common.ResponseDTO<com.ydhl.micro.api.dto.sys.UserDTO> :
     * @Description //管理系统登录
     * @Author Ly
     * @Date 2019/3/30 14:45
     **/
    @ApiOperation(value = "管理系统登录")
    @RequestMapping("gtewxtk1")
    public HttpResultDTO<ResponseLoginDTO> login() throws UnsupportedEncodingException {
        log.info("管理系统登录:{}");
        return weiXinApiClient.getAccountCode();
    }


}
