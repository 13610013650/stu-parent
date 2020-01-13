package com.stu.aspct.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: stu-parent
 * @Package: com.stu.aspct.annotation
 * @ClassName: Log
 * @Author: ZhangSheng
 * @Description: ${description}
 * @Date: 2020/1/13 14:17
 * @Version: 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    String param() default "";

}
