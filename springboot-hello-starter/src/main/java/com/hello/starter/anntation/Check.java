package com.hello.starter.anntation;

import java.lang.annotation.*;

/**
 * @ProjectName: stu-parent
 * @Package: com.hello.starter.anntation
 * @ClassName: Check
 * @Author: ZhangSheng
 * @Description: ${description}
 * @Date: 2020/1/31 18:15
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {

    String value() default "";

}
