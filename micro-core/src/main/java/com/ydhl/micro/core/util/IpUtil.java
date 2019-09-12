package com.ydhl.micro.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IPUtil
 * @Description 获取客户端IP
 * @Author yangll
 * @Date 2019-9-10 11:39:50
 * @Version 1.0
 **/
@Slf4j
public class IpUtil {

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getIpAddr() {
        String ip = null;
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            ip = request.getHeader("x-forwarded-for");
            log.debug("x-forwarded-for ip: {}", ip);
            if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                if (ip.indexOf(",") != -1) {
                    ip = ip.split(",")[0];
                }
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                log.debug("Proxy-Client-IP ip: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                log.debug("WL-Proxy-Client-IP ip: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                log.debug("HTTP_CLIENT_IP ip: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                log.debug("HTTP_X_FORWARDED_FOR ip: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
                log.debug("X-Real-IP ip: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                log.debug("getRemoteAddr ip: {}", ip);
            }
            log.debug("获取客户端ip: {}", ip);
        } catch (Exception e) {
            log.error("获取客户端IP异常！", e);
        }
        return ip;
    }
}
