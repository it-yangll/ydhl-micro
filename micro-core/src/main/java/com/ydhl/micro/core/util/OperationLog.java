package com.ydhl.micro.core.util;

import com.ydhl.micro.core.consts.ModuleEnum;
import com.ydhl.micro.core.consts.OperationEnum;

import java.lang.annotation.*;

/**
 * @ClassName OperationLog
 * @Description 操作日志注解
 * @Author yangll
 * @Date 2019-9-10 11:49:46
 * @Version 1.0
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    public ModuleEnum module();

    public OperationEnum operation();

    public String remark() default "";

}
