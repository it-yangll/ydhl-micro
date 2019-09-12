package com.ydhl.micro.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.api.enumcode.GlobalCodeEnum;
import com.ydhl.micro.api.exception.SystemRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName HeliSmsHelper
 * @Description 合力短信网关发送短信
 * @Author yangll
 * @Date 2019-9-10 14:09:29
 * @Version 1.0
 **/
@Slf4j
public class HeliSmsHelper {

    public static void sendMsg(String loginUrl, String smsUrl, String deviceId, String appId, String mobile, String msg) {
        CacheHelper cacheHelper = null;
        try {
            cacheHelper = SpringUtil.getBean(CacheHelper.class);
            RestTemplate restTemplate = SpringUtil.getBean(RestTemplate.class);

            //登录，获取授权验证码
            JSONObject loginPostData = new JSONObject();
            loginPostData.put("deviceId", deviceId);
            loginPostData.put("appId", appId);
            loginPostData.put("OSName", "");
            loginPostData.put("VNo", "");
            JSONObject loginResult = restTemplate.postForEntity(loginUrl, loginPostData, JSONObject.class).getBody();

            log.info("云平台登录接口响应数据{}", loginResult);
            String token = loginResult.getString("Token");

            //发送短信
            JSONObject msgPostData = new JSONObject();
            msgPostData.put("mobileNum", mobile);
            msgPostData.put("sendContent", msg);
            JSONObject msgResult = restTemplate.postForEntity(smsUrl, msgPostData, JSONObject.class, token).getBody();

            log.info("云平台短信接口响应数据{}", msgResult);
            if (!StringUtils.equalsIgnoreCase(msgResult.getString("Tag"), "1")) {
                throw new SystemRuntimeException(GlobalCodeEnum.ERR_SMS_SEND_FAIL, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_SMS_SEND_FAIL));
            }
        } catch (Exception e) {
            log.error("调用短信网关接口异常！", e);
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_SMS_SEND_FAIL, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_SMS_SEND_FAIL));
        }

    }

    //获取维修信息
    public static JSONObject repairMsg(String loginUrl, String repairUrl, String deviceId, String appId, String VehicleNum, String Location) {
        CacheHelper cacheHelper = null;
        try {
            cacheHelper = SpringUtil.getBean(CacheHelper.class);
            RestTemplate restTemplate = SpringUtil.getBean(RestTemplate.class);

            //登录，获取授权验证码
            JSONObject loginPostData = new JSONObject();
            loginPostData.put("deviceId", deviceId);
            loginPostData.put("appId", appId);
            loginPostData.put("OSName", "");
            loginPostData.put("VNo", "");
            JSONObject loginResult = restTemplate.postForEntity(loginUrl, loginPostData, JSONObject.class).getBody();

            log.info("云平台登录接口响应数据{}", loginResult);
            String token = loginResult.getString("Token");
            //获取维修信息
//            Map<String, String> map = new HashMap<>();
//            map.put("VehicleNum", VehicleNum);
//            map.put("Token", token);
//            map.put("Location", Location);
            String body = restTemplate.getForEntity(repairUrl, String.class, VehicleNum, token).getBody();
            JSONObject obj = JSON.parseObject(body);
            log.info("云平台获取维修信息数据{}", obj);
            if (!StringUtils.equalsIgnoreCase(obj.getString("Tag"), "1")) {
                throw new SystemRuntimeException(GlobalCodeEnum.ERR_REPAIR_FAIL, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_REPAIR_FAIL));
            }
            return obj;
        } catch (Exception e) {
            log.error("调用获取维修信息接口异常！", e);
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_REPAIR_FAIL, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_REPAIR_FAIL));
        }

    }

    //类同步
    public static JSONObject classUpdate(String loginUrl, String classUrl, String deviceId, String appId, String date){
        CacheHelper cacheHelper = null;
        try {
            cacheHelper = SpringUtil.getBean(CacheHelper.class);
            RestTemplate restTemplate = SpringUtil.getBean(RestTemplate.class);

            //登录，获取授权验证码
            JSONObject loginPostData = new JSONObject();
            loginPostData.put("deviceId", deviceId);
            loginPostData.put("appId", appId);
            loginPostData.put("OSName", "");
            loginPostData.put("VNo", "");
            JSONObject loginResult = restTemplate.postForEntity(loginUrl, loginPostData, JSONObject.class).getBody();

            log.info("云平台登录接口响应数据{}", loginResult);
            String token = loginResult.getString("Token");
            //获取信息
            JSONObject msgPostData = new JSONObject();
            JSONObject msg = new JSONObject();
            JSONObject tbClass = new JSONObject(new LinkedHashMap());
            List<String> classItems = new ArrayList<>();
            List<String> featureItems = new ArrayList<>();
            msg.put("CDATE", date.replaceAll("[[\\s-:punct:]]",""));
            msgPostData.put("InField", msg);
            //类
            classItems.add("CLASS");
            classItems.add("KSCHL");
            classItems.add("ATNAM");
            classItems.add("ATBEZ");
            classItems.add("ADATU");
            classItems.add("VDATU");
            tbClass.put("TB_CLASS", classItems);
            //特征值
            featureItems.add("ATNAM");
            featureItems.add("ATBEZ");
            featureItems.add("ATWRT");
            featureItems.add("ATWTB");
            featureItems.add("ADATU");
            featureItems.add("VDATU");
            tbClass.put("TB_CABN", featureItems);
            msgPostData.put("OutTable", tbClass);

            msgPostData.put("SAPName", "ZVEHICLE_GET_CLASS");
            JSONObject msgResult = restTemplate.postForEntity(classUrl, msgPostData, JSONObject.class, token).getBody();
            log.info("云平台获取类特征信息数据{}", msgResult);
            if (!StringUtils.equalsIgnoreCase(msgResult.getString("Tag"), "1")) {
                throw new SystemRuntimeException(GlobalCodeEnum.ERR_REPAIR_FAIL, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_REPAIR_FAIL));
            }
            return msgResult;
        } catch (Exception e) {
            log.error("调用类特征接口异常！", e);
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_REPAIR_FAIL, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_REPAIR_FAIL));
        }
    }

    //整车配置
    public static JSONObject completeUpdate(String loginUrl, String classUrl, String deviceId, String appId, String vin){
        CacheHelper cacheHelper = null;
        try {
            cacheHelper = SpringUtil.getBean(CacheHelper.class);
            RestTemplate restTemplate = SpringUtil.getBean(RestTemplate.class);

            //登录，获取授权验证码
            JSONObject loginPostData = new JSONObject();
            loginPostData.put("deviceId", deviceId);
            loginPostData.put("appId", appId);
            loginPostData.put("OSName", "");
            loginPostData.put("VNo", "");
            JSONObject loginResult = restTemplate.postForEntity(loginUrl, loginPostData, JSONObject.class).getBody();

            log.info("云平台登录接口响应数据{}", loginResult);
            String token = loginResult.getString("Token");
            //获取信息
            JSONObject msgPostData = new JSONObject();
            JSONObject msg = new JSONObject();
            JSONObject tbClass = new JSONObject(new LinkedHashMap());
            List<String> classItems = new ArrayList<>();
            List<String> featureItems = new ArrayList<>();
            msg.put("VHVIN", vin);
            msgPostData.put("InField", msg);
            //档案表
            classItems.add("VHVIN");
            classItems.add("MATNR");
            classItems.add("MAKTX");
            classItems.add("ClASS");
            classItems.add("KSCHL");
            classItems.add("SHDATE");
            classItems.add("PRICE");
            tbClass.put("TB_VEHICLE", classItems);
            //配置表
//            featureItems.add("ATINN");
            featureItems.add("ATNAM");
            featureItems.add("ATBEZ");
            featureItems.add("ATWRT");
            featureItems.add("ATWTB");
            tbClass.put("TB_CONFIGURE", featureItems);
            msgPostData.put("OutTable", tbClass);

            msgPostData.put("SAPName", "ZVEHICLE_ARCHIVES");
            JSONObject msgResult = restTemplate.postForEntity(classUrl, msgPostData, JSONObject.class, token).getBody();
            log.info("云平台获取类特征信息数据{}", msgResult);
            if (!StringUtils.equalsIgnoreCase(msgResult.getString("Tag"), "1")) {
                throw new SystemRuntimeException(GlobalCodeEnum.ERR_REPAIR_FAIL, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_REPAIR_FAIL));
            }
            return msgResult;
        } catch (Exception e) {
            log.error("调用类特征接口异常！", e);
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_REPAIR_FAIL, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_REPAIR_FAIL));
        }
    }

    //技术参数
    public static JSONObject tenchUpdate(String loginUrl, String classUrl, String deviceId, String appId, String vin){
        CacheHelper cacheHelper = null;
        try {
            cacheHelper = SpringUtil.getBean(CacheHelper.class);
            RestTemplate restTemplate = SpringUtil.getBean(RestTemplate.class);

            //登录，获取授权验证码
            JSONObject loginPostData = new JSONObject();
            loginPostData.put("deviceId", deviceId);
            loginPostData.put("appId", appId);
            loginPostData.put("OSName", "");
            loginPostData.put("VNo", "");
            JSONObject loginResult = restTemplate.postForEntity(loginUrl, loginPostData, JSONObject.class).getBody();

            log.info("云平台登录接口响应数据{}", loginResult);
            String token = loginResult.getString("Token");
            //获取信息
            JSONObject msgPostData = new JSONObject();
            JSONObject msg = new JSONObject();
            JSONObject tbClass = new JSONObject();
            List<String> classItems = new ArrayList<>();
            List<String> featureItems = new ArrayList<>();
            msg.put("SERNR", vin);
            msgPostData.put("InField", msg);
            //档案表
            classItems.add("MAKEDATE");
            classItems.add("EDQZL");
            classItems.add("ZHIZH");
            classItems.add("MENJIA");
            classItems.add("MOTORNUMBER");
            classItems.add("MENJIA_HIGH");
            classItems.add("WERKS");
            tbClass.put("TB_VEHICLE", classItems);
            msgPostData.put("OutTable", tbClass);

            msgPostData.put("SAPName", "ZVEHICLE_TECHNICAL_GET");
            JSONObject msgResult = restTemplate.postForEntity(classUrl, msgPostData, JSONObject.class, token).getBody();
            log.info("云平台获取类特征信息数据{}", msgResult);
            if (!StringUtils.equalsIgnoreCase(msgResult.getString("Tag"), "1")) {
                throw new SystemRuntimeException(GlobalCodeEnum.ERR_REPAIR_FAIL, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_REPAIR_FAIL));
            }
            return msgResult;
        } catch (Exception e) {
            log.error("调用类特征接口异常！", e);
            throw new SystemRuntimeException(GlobalCodeEnum.ERR_REPAIR_FAIL, cacheHelper.msgTemplate(GlobalCodeEnum.ERR_REPAIR_FAIL));
        }
    }
}
