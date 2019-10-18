package com.ydhl.micro.base.weixin.util;

import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.api.dto.liteapp.weixin.WXSecretDto;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.base.weixin.dto.BeanWxDTO;
import com.ydhl.micro.core.util.CacheHelper;
import com.ydhl.micro.core.util.PayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

/**
 * @ClassName WxConfigUtil
 * @Description TODO
 * @Author yangll
 * @Date 2019/9/19 0019 19:25
 * @Version 1.0
 **/
@Component
@Slf4j
public class WxConfigUtil {

    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${token_exp_time}")
    private long tokenExpTime;

    @Value("${wx.code2session}")
    private String wxCode2SessionUrl;

    @Value("${wx.litewx.corpid}")
    private String wxCorpid;

    @Value("${wx.litewx.corpsecret}")
    private String wxSecret;

    @Value("${wx.litewx.agentId}")
    private String agentId;

    @Value("${wx.oauthCode}")
    private String oauthCodeUrl;

    @Value("${wx.encod}")
    private String encodUrl;

    @Value("${wx.user.oauthUserId}")
    private String oauthUserIdUrl;

    @Value("${wx.user.oauthUserInfo}")
    private String oauthUserInfoUrl;

    public WxConfigUtil() {
        log.info("==========================WXConfigUtil==============================");
        log.info("====================================================================");
        log.info("====================================================================");
        log.info("====================================================================");
        log.info("====================================================================");
        log.info("================================启==================================");
        log.info("================================动==================================");
        log.info("================================成==================================");
        log.info("================================功==================================");
        log.info("====================================================================");
        log.info("====================================================================");
        log.info("====================================================================");
        log.info("====================================================================");
        log.info("====================================================================");
        log.info("====================================================================");
        log.info("====================================================================");
        log.info("====================================================================");
        log.info("====================================================================");

    }


    /**
     * 将凭证存入缓存
     * @return
     */
    @PostConstruct
    public void getAccountToken() {
        ResponseEntity<String> wxResponse = restTemplate.getForEntity(wxCode2SessionUrl,String.class,wxCorpid,wxSecret);

        if (!wxResponse.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN));
        }
        WXSecretDto wxSecretDto = JSONObject.parseObject(wxResponse.getBody(),WXSecretDto.class);

        if (wxSecretDto.getErrcode() > 0) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }
        //微信登录凭证放入redis
        cacheHelper.saveWXSession(wxCorpid, wxSecretDto, tokenExpTime);
        log.info("=================================WX_AUTO========================================");

    }


    /**
     * 获取用户信息
     * @param code
     * @return
     */
    public ResponseEntity<String> getWxUserInfo(String code, String state){
        String accountCode = null;
        WXSecretDto access_token = cacheHelper.getWxAccessToken(wxCorpid);
        if(access_token.getErrcode() < 1){
            accountCode = access_token.getAccess_token();
        }else{
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN));
        }
        //用户id获取
        ResponseEntity<String> wxResponse = restTemplate.getForEntity(oauthUserIdUrl,String.class,accountCode,code);
        if (!wxResponse.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_ACCESS_USERID_TOKEN, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_ACCESS_USERID_TOKEN));
        }
        BeanWxDTO userId = BeanWxDTO.getUserIdBody(wxResponse.getBody());
        cacheHelper.saveWXBeanString(((BeanWxDTO.UserId) userId).getUserId(), userId, tokenExpTime);
        //用户信息获取
        ResponseEntity<String> wxResponseUserInfo = restTemplate.getForEntity(oauthUserInfoUrl, String.class,accountCode,((BeanWxDTO.UserId) userId).getUserId());
        if (!wxResponseUserInfo.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }
        return wxResponseUserInfo;
    }


    /**
     * 路劲归位
     * @param url
     * @param wxCorpid
     * @param encodUrl
     * @param agentId
     * @param randomCode
     * @return
     */
    public String newMsgTemplate(String url,String wxCorpid,String encodUrl,String agentId,String randomCode){
        return MessageFormat.format(url,wxCorpid,encodUrl,agentId,randomCode);
    }

    /**
     * 多参数路劲归位
     * @param url
     * @param params
     * @return
     */
    public String newMsgTemplateParams(String url,Object... params){
        return MessageFormat.format(url,params);
    }



    public String redirectWXAuthUrl(){
        try {
            return newMsgTemplate(oauthCodeUrl, URLEncoder.encode(wxCorpid,"UTF-8"),URLEncoder.encode(encodUrl,"UTF-8"),URLEncoder.encode(agentId,"UTF-8"), PayUtil.createCode(6));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN));
        }
    }


}
