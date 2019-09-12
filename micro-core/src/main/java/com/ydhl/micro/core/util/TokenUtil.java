package com.ydhl.micro.core.util;

import com.ydhl.micro.core.security.JwtUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TokenUtil
 * @Description 获取当前登录用户令牌
 * @Author yangll
 * @Date 2019-9-10 11:40:42
 * @Version 1.0
 **/
public class TokenUtil {

    public static String getToken() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request.getHeader(JwtUtil.JWT_NAME);
    }

}
