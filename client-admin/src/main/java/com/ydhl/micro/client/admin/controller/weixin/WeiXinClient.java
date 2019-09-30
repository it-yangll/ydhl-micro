package com.ydhl.micro.client.admin.controller.weixin;

import com.ydhl.micro.api.dto.admin.sys.login.ResponseLoginDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import com.ydhl.micro.client.admin.service.weixin.WeiXinApiClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

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
    @Autowired
    private RestTemplate restTemplate;


    /**
     * @param  :
     * @return com.ydhl.micro.api.dto.common.ResponseDTO<com.ydhl.micro.api.dto.sys.UserDTO> :
     * @Description //管理系统登录
     * @Author Ly
     * @Date 2019/3/30 14:45
     **/
    @ApiOperation(value = "管理系统登录")
    @RequestMapping("gtewxtk")
    public HttpResultDTO<ResponseLoginDTO> login() {
        log.info("管理系统登录:{}");
        return weiXinApiClient.gtewxtk();
    }

    @ApiOperation(value = "微信获取当前用户信息")
    @RequestMapping("/public/redirectUriCode")
    public HttpResultDTO redirectUriCode(HttpServletRequest request, HttpServletResponse response){
        //从request里面获取code参数(当微信服务器访问回调地址的时候，会把code参数传递过来)
        String code = request.getParameter("code");

        log.info("code:",code);

        String encodUrl = "http://iqvu2j.natappfree.cc/api/admin/public/MTAuthorization";

        //获取code后，请求以下链接获取access_token
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=wx6a926e35be324c15&redirect_uri=+"+URLEncoder.encode(encodUrl)+"&" +
                "response_type=code&" +
                "scope=snsapi_privateinfo&" +
                "agentid=1000021&" +
                "state=STATE#wechat_redirect";



        ResponseEntity<String> wxResponse = restTemplate.getForEntity(url,String.class);


        log.info("wxResponse",wxResponse);



        return HttpResultDTO.ok(wxResponse.getBody());


    }




    @RequestMapping(value = "/public/MTAuthorization")
    @ResponseBody
    public HttpResultDTO mTAuthorization(HttpServletRequest request, HttpSession session) {
        String code= request.getParameter("code");
        String state=request.getParameter("state");

        log.info("wx_code---------------------------:"+code);
        log.info("wx_code---------------------------:"+code);
        log.info("wx_code---------------------------:"+code);
        log.info("wx_code---------------------------:"+code);
        log.info("wx_code---------------------------:"+code);
        log.info("wx_code---------------------------:"+code);
        log.info("wx_code---------------------------:"+code);
        log.info("wx_code---------------------------:"+code);

        return HttpResultDTO.ok(code);
    }





}
