package com.ydhl.micro.base.weixin.util;

import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.api.dto.liteapp.weixin.WXSecretDto;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.core.util.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * @ClassName WxConfigUtil
 * @Description TODO
 * @Author yangll
 * @Date 2019/9/19 0019 19:25
 * @Version 1.0
 **/
@Component
public class WxConfigUtil {


    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${token_exp_time}")
    private long tokenExpTime;

    @Value("${wx.code2sessionUrl}")
    private String wxCode2SessionUrl;

    @Value("${wx.litewx.corpid}")
    private String wxCorpid;

    @Value("${wx.litewx.corpsecret}")
    private String wxSecret;


    /**
     * 将凭证存入缓存
     * @return
     */
    public ResponseEntity<String> getAccountToken() {
        ResponseEntity<String> wxResponse = restTemplate.getForEntity(wxCode2SessionUrl,String.class,wxCorpid,wxSecret);

        if (!wxResponse.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN));
        }
        WXSecretDto wxSecretDto = JSONObject.parseObject(wxResponse.getBody(),WXSecretDto.class);

        if (wxSecretDto.getErrcode() > 0) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }

        //微信登录凭证放入redis
        cacheHelper.saveWXSession(WXSecretDto.WX_CACHETOKEN_NAME, wxSecretDto, tokenExpTime);
        return wxResponse;
    }


    /**
     * 路劲归位
     * @param url
     * @param obj
     * @return
     */
    public static String newMsgTemplate(String url,Object ... obj){
        return MessageFormat.format(url,obj);
    }










}
