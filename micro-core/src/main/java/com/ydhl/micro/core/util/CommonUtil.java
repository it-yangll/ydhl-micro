package com.ydhl.micro.core.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentParser;
import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.api.enumcode.consts.OrderType;
import com.ydhl.micro.api.enumcode.consts.SourceType;
import com.ydhl.micro.core.consts.RedisKeyConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.spec.AlgorithmParameterSpec;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @ClassName CommonUtil
 * @Description 系统内部工具类
 * @Author yangll
 * @Date 2019-9-10 11:41:31
 * @Version 1.0
 **/
@Slf4j
@Component
public class CommonUtil {

    @Autowired
    private CacheHelper cacheHelper;

    /** 追加删除标识串 */
    public static String appendDeleteUnique(String val) {
        return val.concat("-").concat(String.valueOf(System.currentTimeMillis())).concat("-DELETE");
    }

    /** 移除删除标识串 */
    public static String removeDeleteUnique(String val) {
        if (StringUtils.isBlank(val) || val.length() < 21 || !val.endsWith("-DELETE")) {
            return val;
        }
        return StringUtils.left(val, val.length() - 21);
    }

    /** 判断请求是否是IE浏览器 */
    public static boolean isMSBrowser() {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String ua = requestAttributes.getRequest().getHeader("User-Agent");
            log.debug("浏览器标识:{}", ua);
            UserAgent userAgent = UserAgentParser.parse(ua);
            log.debug("解析User-Agent信息对象【{}】", JSONObject.toJSONString(userAgent));

            String[] IEBrowserSignals = {"MSEdge", "MSIE"};
            for (String signal : IEBrowserSignals) {
                if (userAgent.getBrowser().getName().toUpperCase().contains(signal.toUpperCase())) {
                    log.debug("该请求来自于微软浏览器【{}】", signal);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            log.error("判断是否是IE浏览器异常！", e);
        }
        return false;
    }

    /**
     * @return java.lang.String :
     * @Description //格式化浏览器标识
     * @Author yangll
     * @Date 2019-9-10 11:41:47
     **/
    public static String formatUserAgent() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String ua = requestAttributes.getRequest().getHeader("User-Agent");
        log.debug("浏览器标识:{}", ua);
        UserAgent userAgent = UserAgentParser.parse(ua);
        StringBuilder sb = new StringBuilder();
        sb.append("平台类型").append("：").append(userAgent.getPlatform().getName()).append("<br /><br />");
        sb.append("系统类型").append("：").append(userAgent.getOs().getName()).append("<br /><br />");
        sb.append("浏览器类型").append("：").append(userAgent.getBrowser().getName()).append("<br /><br />");
        sb.append("浏览器版本").append("：").append(userAgent.getVersion()).append("<br /><br />");
        sb.append("引擎类型").append("：").append(userAgent.getEngine().getName()).append("<br /><br />");
        sb.append("引擎版本").append("：").append(userAgent.getEngineVersion()).append("<br /><br />");
        sb.append("是否为移动端").append("：").append(userAgent.isMobile() ? "是" : "否");
        return sb.toString();
    }

    /** 获取服务器本机IP */
    public static String getLocalIp() {
        try {
            //用 getLocalHost() 方法创建的InetAddress的对象
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取服务器本机IP出错！", e);
        }
        return "127.0.0.1";
    }

    /**
     * @param fileName :文件名
     * @return java.lang.String :
     * @Description //对文件名进行转码
     * @Author yangll
     * @Date 2019-9-10 11:42:03
     **/
    public static String encodeExportFileName(String fileName) {
        //中文转码，防止乱码
        try {
            if (CommonUtil.isMSBrowser()) {
                // IE浏览器
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                // 非IE浏览器
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
        } catch (Exception e) {
            log.error("导出文件名转码异常！", e);
        }
        return fileName;
    }

    /**
     * @param sourceType :订单来源
     * @param orderType  :订单类型
     * @return java.lang.String :
     * @Description //生成订单流水号
     * @Author yangll
     * @Date 2019-9-10 11:42:15
     **/
    public String generateSerialNumber(SourceType sourceType, OrderType orderType) {
        String key = RedisKeyConst.getOmsOrderSequenceKey();
        String sequence = cacheHelper.getStringValue(key);
        if (StringUtils.isBlank(sequence)) {
            cacheHelper.setStringValue(key, "0", 86400);
        }
        return buildSerialNumber(key, StringUtils.left(sourceType.name(), 1).concat(StringUtils.left(orderType.name(), 1)));
    }

    /** 生成回收订单流水号 */
    public String generateRecOrderSerialNumber() {
        String key = RedisKeyConst.getRecOrderSequenceKey();
        String sequence = cacheHelper.getStringValue(key);
        if (StringUtils.isBlank(sequence)) {
            cacheHelper.setStringValue(key, "0", 86400);
        }
        return buildSerialNumber(key, "REC");
    }

    /**
     * @param key    :流水号缓存类型key
     * @param prefix :流水号前缀
     * @return java.lang.String :
     * @Description //生成流水号
     * @Author yangll
     * @Date 2019-9-10 11:42:26
     **/
    private String buildSerialNumber(String key, String prefix) {
        DecimalFormat df = new DecimalFormat("0000");
        String num = df.format(cacheHelper.increment(key));
        //流水号格式 yyMMdd-prefix-顺序id-HHmmssSSS
        String serialNumber = DateUtil.format(new Date(), "yyMMdd");
        serialNumber = serialNumber.concat("-").concat(prefix).concat("-");
        serialNumber = serialNumber.concat(num);
        serialNumber = serialNumber.concat("-");
        serialNumber = serialNumber.concat(DateUtil.format(new Date(), "HHmmssSSS"));
        return serialNumber;
    }

    /**
     * 解密微信用户电话
     *
     * @param sessionkey    微信登录凭证
     * @param ivStr         加密算法的初始向量
     * @param encryptedData 带解密数据
     * @return
     * @throws Exception
     * @author pangxianhe
     * @date 2018年10月22日
     */
    public static WxPhoneNumber decrypt(String sessionkey, String ivStr, String encryptedData) throws Exception {

        byte[] encData = Base64.decode(encryptedData);
        byte[] iv = Base64.decode(ivStr);
        byte[] key = Base64.decode(sessionkey);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        String data = new String(cipher.doFinal(encData), "UTF-8");
        return JSONObject.toJavaObject(JSONObject.parseObject(data), WxPhoneNumber.class);
    }

}
