package com.ydhl.micro.core.util;

import java.lang.annotation.*;

/**
 * @ClassName NonAfterLog
 * @Description 不打印响应日志注解
 * @Author yangll
 * @Date 2019-9-10 11:50:09
 * @Version 1.0
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonAfterLog {

}
