package com.ydhl.micro.base.filter;

import com.ydhl.micro.base.dao.auto.SysLogMapper;
import com.ydhl.micro.base.entity.SysLog;
import com.ydhl.micro.core.security.JwtBody;
import com.ydhl.micro.core.security.JwtUtil;
import com.ydhl.micro.core.util.CommonUtil;
import com.ydhl.micro.core.util.IpUtil;
import com.ydhl.micro.core.util.OperationLog;
import com.ydhl.micro.core.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName LogAspect
 * @Description 操作日志切面
 * @Author yangll
 * @Date 2019-9-10 11:46:29
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    @Autowired
    private SysLogMapper logMapper;
    private ThreadLocal<Long> logId = new ThreadLocal<>();
    private ThreadLocal<Long> logTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.ydhl.micro.core.util.OperationLog)")
    public void OperationLogPoint() {
    }

    @Before("OperationLogPoint()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        OperationLog operationLog = methodSignature.getMethod().getAnnotation(OperationLog.class);

        String[] strings = methodSignature.getParameterNames();

        Map<String, String> params = new LinkedHashMap<>();
        params.put("Method-Params-Key", Arrays.toString(strings));
        params.put("Method-Params-Value", Arrays.toString(joinPoint.getArgs()));

        saveLog(methodSignature.getDeclaringTypeName().concat(".").concat(methodSignature.getName()), params.toString(), operationLog);
    }

    @AfterReturning(returning = "ret", pointcut = "OperationLogPoint()")
    public void doAfterReturning(Object ret) {
        updateLog(ret == null ? null : ret.toString());
    }

    @AfterThrowing(throwing = "ex", pointcut = "OperationLogPoint()")
    public void doAfterThrowing(Throwable ex) {
        updateLog(ex.getMessage());
    }

    private void saveLog(String method, String params, OperationLog operationLog) {
        try {
            SysLog sysLog = new SysLog();
            sysLog.setPlatform(getPlatform());
            sysLog.setCreateTime(new Date());
            sysLog.setIp(IpUtil.getIpAddr());
            sysLog.setMethod(method);
            sysLog.setModule(operationLog.module().name());
            sysLog.setOperation(operationLog.operation().name());
            sysLog.setParams(StringUtils.left(params, 5000));
            sysLog.setRemark(StringUtils.left(operationLog.remark(), 500));
            sysLog.setUserAgent(CommonUtil.formatUserAgent());

            if (StringUtils.isNotBlank(TokenUtil.getToken())) {
                JwtBody jwtBody = JwtUtil.parseToJwtBody(TokenUtil.getToken());
                sysLog.setUserId(jwtBody.getPk());
                sysLog.setUserName(jwtBody.getName());
            }
            logMapper.insertSelective(sysLog);
            logId.set(sysLog.getId());
            logTime.set(System.currentTimeMillis());
        } catch (Exception e) {
            log.error("保存操作日志异常！", e);
        }
    }

    private void updateLog(String ret) {
        try {
            if (StringUtils.isNotBlank(ret)) {
                SysLog sysLog = new SysLog();
                sysLog.setId(logId.get());
                sysLog.setResult(StringUtils.left(ret, 5000));
                sysLog.setTotalMillis(String.valueOf(System.currentTimeMillis() - logTime.get()));
                sysLog.setModifyTime(new Date());
                logMapper.updateByPrimaryKeySelective(sysLog);
            }
        } catch (Exception e) {
            log.error("保存操作日志异常！", e);
        }
        logId.remove();
        logTime.remove();
    }

    private String getPlatform() {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String platform = requestAttributes.getRequest().getHeader("platform");
            if (StringUtils.isNotBlank(platform)) {
                switch (platform) {
                    case "admin":
                        return "运营平台";

                    case "mall":
                        return "商城";

                    case "liteapp":
                        return "小程序";

                    default:
                        return "系统";
                }
            } else {
                platform = requestAttributes.getRequest().getHeader("x-forwarded-prefix");
                if (StringUtils.isNotBlank(platform)) {
                    switch (platform) {
                        case "/api/admin":
                            return "运营平台";

                        case "/api/mall":
                            return "商城";

                        case "/api/liteapp":
                            return "小程序";

                        default:
                            return "系统";
                    }
                }
            }
        } catch (Exception e) {
            log.error("获取当前操作所属平台异常！", e);
        }
        return "未知";
    }
}
