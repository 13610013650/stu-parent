package com.java.clazz.annotation;

import java.lang.annotation.*;

/**
 * @ProjectName: stu-parent
 * @Package: com.java.clazz.annotation
 * @ClassName: Test
 * @Author: ZhangSheng
 * @Description: ${description}
 * @Date: 2020/1/18 23:21
 * @Version: 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

    String value() default "";

}
