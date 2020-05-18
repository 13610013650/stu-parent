package com.stu.valied.excption;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义参数校验注解类
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { TestParamValied.class })
public @interface StringParamValied {

    String message() default "{com.stu.valied.excption.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };



}
