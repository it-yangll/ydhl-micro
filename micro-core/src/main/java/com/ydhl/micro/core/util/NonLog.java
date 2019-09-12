package com.ydhl.micro.core.util;

import java.lang.annotation.*;

/**
 * @ClassName NonAfterLog
 * @Description 不打印请求和响应日志注解
 * @Author yangll
 * @Date 2019-9-10 11:40:31
 * @Version 1.0
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonLog {

}
