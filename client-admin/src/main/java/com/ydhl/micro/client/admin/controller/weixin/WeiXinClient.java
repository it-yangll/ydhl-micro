package com.ydhl.micro.client.admin.controller.weixin;

import com.ydhl.micro.api.dto.admin.sys.login.ResponseLoginDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.weixin.WeiXinApiClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName AuthenticationController
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-20 13:22:22
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(description = "微信基本接口")
public class WeiXinClient {

    @Autowired
    private WeiXinApiClient weiXinApiClient;

    @Value("${wx.encodUrl}")
    private String encodUrl;

    @Value("${wx.litewx.corpid}")
    private String openId;

    /**
     * @param  :
     * @return com.ydhl.micro.api.dto.common.ResponseDTO<com.ydhl.micro.api.dto.sys.UserDTO> :
     * @Description //管理系统登录
     * @Author Ly
     * @Date 2019/3/30 14:45
     **/
    @ApiOperation(value = "获取网页跳转连接")
    @RequestMapping("/public/gtewxtk")
    public HttpResultDTO<ResponseLoginDTO> login() {
        log.info("管理系统登录:{}");
        try {
            return weiXinApiClient.getAccountCode();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return HttpResultDTO.error("error");
    }


    @ApiModelProperty(value = "获取当前微信登录用户角色权限")
    @RequestMapping(value = "/public/MTAuthorization")
    @ResponseBody
    public HttpResultDTO authorizationWx(HttpServletRequest request, HttpSession session) {
        String code= request.getParameter("code");
        String state=request.getParameter("state");
        return weiXinApiClient.getWXUserInfo(code,state);
    }

    @ApiModelProperty(value = "获取当前微信登录用户角色权限,保留接口")
    @RequestMapping(value = "/public/MTAuthorizationRetain")
    @ResponseBody
    public HttpResultDTO mTAuthorization(HttpServletRequest request, HttpSession session) {
        String code= request.getParameter("code");
        String state=request.getParameter("state");
        return weiXinApiClient.getWXUserInfo(code,state);
    }





}
