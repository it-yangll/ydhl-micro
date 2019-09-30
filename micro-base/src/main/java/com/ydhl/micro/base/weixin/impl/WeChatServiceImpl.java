package com.ydhl.micro.base.weixin.impl;

import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.api.dto.liteapp.member.ResponseWxSessionDTO;
import com.ydhl.micro.api.dto.liteapp.member.WxCode2SessionDTO;
import com.ydhl.micro.api.dto.liteapp.weixin.WXSecretDto;
import com.ydhl.micro.api.dto.mall.member.CaptchaDTO;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import com.ydhl.micro.api.enumcode.consts.CaptchaMoudleEnum;
import com.ydhl.micro.api.enumcode.mall.MallCodeEnum;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import com.ydhl.micro.base.weixin.WeChatService;
import com.ydhl.micro.base.weixin.dto.BindWxDTO;
import com.ydhl.micro.base.weixin.util.WxConfigUtil;
import com.ydhl.micro.core.consts.RedisKeyConst;
import com.ydhl.micro.core.util.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



/**
 * @ClassName WeChatServiceImpl
 * @Description weixin
 * @Author yangll
 * @Date 2019/9/18 0018 17:10
 * @Version 1.0
 **/
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WxConfigUtil wxConfigUtil;

    @Value("${token_exp_time}")
    private long tokenExpTime;

    @Value("${wx.code2sessionUrl}")
    private String wxCode2SessionUrl;

    @Value("${wx.litewx.corpid}")
    private String wxCorpid;

    @Value("${wx.litewx.corpsecret}")
    private String wxSecret;


    public ResponseEntity<String> getAccountToken() {
        ResponseEntity<String> wxResponse = restTemplate.getForEntity(wxCode2SessionUrl,String.class,wxCorpid,wxSecret);

        if (!wxResponse.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_ACCESS_TOKEN));
        }
        WXSecretDto wxSecretDto = JSONObject.parseObject(wxResponse.getBody(),WXSecretDto.class);

        log.info("微信登录凭证：{}", wxResponse);
        if (wxSecretDto.getErrcode() > 0) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }

        //微信登录凭证放入redis
        cacheHelper.saveWXSession(WXSecretDto.WX_CACHETOKEN_NAME, wxSecretDto, tokenExpTime);
        return wxResponse;
    }

    @Override
    public String redirectUriCode(String code) {
        return  wxConfigUtil.redirectUriCode(code);
    }


    @Override
    public ResponseWxSessionDTO bindWx(BindWxDTO dto) {
        ResponseEntity<String> wxResponse = restTemplate.getForEntity(wxCode2SessionUrl, String.class, wxCorpid, wxSecret, dto.getCode());

        if (!wxResponse.getStatusCode().equals(HttpStatus.OK)) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }
        WxCode2SessionDTO wxCode2SessionDTO = JSONObject.parseObject(wxResponse.getBody(),WxCode2SessionDTO.class);

        log.info("微信登录凭证：{}", wxResponse);
        if (wxCode2SessionDTO.getSession_key() == null) {
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_WX_CODE2SESSION, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_WX_CODE2SESSION));
        }

        //微信登录凭证放入redis
        cacheHelper.saveWxSession(wxCode2SessionDTO.getOpenid(), wxCode2SessionDTO, tokenExpTime);
        return ResponseWxSessionDTO.transform(wxCode2SessionDTO);
    }


    /** 校验验证码是否正确 */
    private void checkCaptcha(String mobile, String code, CaptchaMoudleEnum captchaMoudleEnum) {
        String captcha = cacheHelper.getMobileCaptcha(mobile, captchaMoudleEnum);
        if (StringUtils.isBlank(captcha) || !StringUtils.equalsIgnoreCase(code, captcha)) {
            throw new SystemRuntimeException(MallCodeEnum.ERR_CAPTCHA, cacheHelper.msgTemplate(MallCodeEnum.ERR_CAPTCHA));
        }
    }


    @Override
    public void captcha(CaptchaDTO dto) {
        //1.生成验证码
        String captcha = RandomStringUtils.randomNumeric(1);

        //2.校验验证码发送间隔和发送次数
        String captchaCache = cacheHelper.getMobileCaptcha(dto.getMobile(), dto.getMoudle());
        if (StringUtils.isNotBlank(captchaCache)) {
            Long expireTime = cacheHelper.getMobileCaptchaExpire(dto.getMobile(), dto.getMoudle());
            int survivalTime = 1 * 60;
            if ((expireTime + 60) > survivalTime) {
                throw new SystemRuntimeException(GlobalCodeEnum.ERR_COMMON, cacheHelper.msgTemplate("ERR_SMS_INTERVAL", "发送短信过于频繁，请稍后重试"));
            }
        }

        String key = RedisKeyConst.getMobileSmsSequenceKey(dto.getMobile());
        String sendCout = cacheHelper.getStringValue(key);
        if (StringUtils.isBlank(sendCout)) {
            cacheHelper.setStringValue(key, "1", 86400);
        } else {
            if (cacheHelper.increment(key) > 1) {
                throw new SystemRuntimeException(GlobalCodeEnum.ERR_COMMON, cacheHelper.msgTemplate("ERR_SMS_TOO_MANY", "该手机【{0}】今天发送的短信次数已超过{1}条", dto.getMobile(), 1));
            }
        }

        //3.验证码存入到redis
        cacheHelper.saveMobileCaptcha(dto.getMobile(), dto.getMoudle(), captcha, 1);

        //4.短信模板
        String smsTemplate = cacheHelper.msgTemplate(dto.getMoudle().name(), "验证码：{0}，{1}分钟内有效", captcha, 1);

        log.info("手机验证码模板：{}", smsTemplate);

        //4.调用短信网关发送短信...
       //HeliSmsHelper.sendMsg(heliLoginUrl, heliSmsUrl, heliDeviceId, heliAppId, dto.getMobile(), smsTemplate);
    }




}
