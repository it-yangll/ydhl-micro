package com.ydhl.micro.core.filter;

import com.alibaba.fastjson.JSONObject;
import com.ydhl.micro.core.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName LogAspect
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 13:29:26
 * @Version 1.0
 **/
@Aspect
@Component
@Configuration
@Slf4j
public class LogAspect {

    /**
     * @param joinPoint :
     * @return void :
     * @Description //前置通知，方法调用前被调用
     * @Author yangll
     * @Date 2019-9-10 13:30:11
     **/
    @Before("execution(public * com.ydhl.micro..controller..*.*(..)) && !@annotation(com.ydhl.micro.core.util.NonBeforeLog) && !@annotation(com.ydhl.micro.core.util.NonLog)")
    public void doBefore(JoinPoint joinPoint) {
        try {
            Map<String, Object> info = new LinkedHashMap<>();
            info.put("Aop", "前置通知");
            //获取目标方法的参数信息
            Object[] obj = joinPoint.getArgs();
            Signature signature = joinPoint.getSignature();

            //AOP代理类的类（class）信息
            info.put("Method-Class", signature.getDeclaringType());
            //AOP代理类的名字
            info.put("Method-Package", signature.getDeclaringTypeName());
            //代理的是哪一个方法
            info.put("Method-Name", signature.getName());

            MethodSignature methodSignature = (MethodSignature) signature;
            String[] strings = methodSignature.getParameterNames();

            info.put("Method-Params-Key", Arrays.toString(strings));
            info.put("Method-Params-Value", Arrays.toString(joinPoint.getArgs()));

            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                if (request != null) {
                    info.put("Request-ServletPath", request.getServletPath());
                    info.put("Request-RequestURL", request.getRequestURL());

                    info.put("Request-ServerName", request.getServerName());
                    info.put("Request-ServerPort", request.getServerPort());

                    info.put("Request-ContentType", request.getContentType());
                    info.put("Request-CharacterEncoding", request.getCharacterEncoding());

                    info.put("Request-SessionId", request.getRequestedSessionId());
                    info.put("Request-Cookies", request.getCookies());

                    Enumeration headerNames = request.getHeaderNames();
                    while (headerNames.hasMoreElements()) {
                        String key = (String) headerNames.nextElement();
                        String value = request.getHeader(key);
                        info.put("Request-Header-" + key, value);
                    }

                    info.put("Request-ParameterMap", request.getParameterMap());
                    info.put("Request-QueryString", request.getQueryString());

                    info.put("Request-RemoteHost", request.getRemoteHost());
                    info.put("Request-RemotePort", request.getRemotePort());
                    info.put("Request-RemoteAddr", request.getRemoteAddr());
                    info.put("Request-RemoteUser", request.getRemoteUser());
                    info.put("Request-RemoteIP", IpUtil.getIpAddr());
                }
            }

            log.info(formatVal(info));
        } catch (Exception e) {
            log.error("拦截记录日志异常：", e);
        }
    }

    /**
     * @param ret :
     * @return void :
     * @Description //处理完请求返回内容
     * @Author yangll
     * @Date 2019-9-10 13:44:04
     **/
    @AfterReturning(returning = "ret", pointcut = "execution(public * com.ydhl.micro..controller..*.*(..)) && !@annotation(com.ydhl.micro.core.util.NonAfterLog) && !@annotation(com.ydhl.micro.core.util.NonLog)")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("Aop", "后置通知");
        info.put("out-params", ret);
        log.info(formatVal(info));
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    private String formatVal(Object o) {
        JSONObject result = new JSONObject();
        result.put("timestamp", LocalDateTime.now().format(formatter));
        result.put("thread-id", Thread.currentThread().getId());
        result.put("thread-name", Thread.currentThread().getName());
        result.put("body", o);
        return result.toJSONString();
    }

}

