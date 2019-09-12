package com.ydhl.micro.core.util;

import java.lang.annotation.*;

/**
 * @ClassName NonBeforeLog
 * @Description 不打印请求日志注解
 * @Author yangll
 * @Date 2019-9-10 11:49:57
 * @Version 1.0
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonBeforeLog {

}
