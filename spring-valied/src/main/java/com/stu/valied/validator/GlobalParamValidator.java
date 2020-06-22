package com.stu.valied.validator;


import com.alibaba.fastjson.JSON;
import com.stu.valied.utils.R;
import com.stu.valied.utils.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常捕获并统一返回结果类
 */
@Slf4j
@RestControllerAdvice
public class GlobalParamValidator {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R exceptionHandler(MethodArgumentNotValidException  exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Map<String, String> collect = fieldErrors.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        R result = new R();
        if (!CollectionUtils.isEmpty(collect)){
            result.setMsg(JSON.toJSONString(collect));
        }
        log.error(JSON.toJSONString(collect));
        return result.setCode(ResponseStatus.DO_FAIL.getCode());
    }


    @ExceptionHandler(Throwable.class)
    public R exceptionHandler(Throwable e){
        StackTraceElement stackTrace = e.getStackTrace()[0];
        StringBuilder message = new StringBuilder();
        message.append(stackTrace.getClassName());
        message.append(":");
        message.append(stackTrace.getMethodName());
        message.append(",ExceptionInfo:");
        message.append(e.getMessage());
        log.error(message.toString());
        return new R(ResponseStatus.SYS_EXCEPTION.getCode(),message.toString());
    }

    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e){
       StringBuilder message = new StringBuilder();
       message.append(e.getClass());
       message.append(":");
       message.append(" ExceptionInfo:");
       message.append(e.getMessage());
       StackTraceElement[] stackTrace = e.getStackTrace();
       for (int i = 0; i < stackTrace.length; i++) {
           System.out.println(stackTrace[i].getClassName()+":"+stackTrace[i].getMethodName());
       }
       log.error(message.toString());
       return new R(ResponseStatus.SYS_EXCEPTION.getCode(),message.toString());
    }

}
