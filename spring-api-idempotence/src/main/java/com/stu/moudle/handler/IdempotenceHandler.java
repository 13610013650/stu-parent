package com.stu.moudle.handler;

import com.stu.moudle.annotation.ApiIdempotence;
import com.stu.moudle.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 保证接口幂等性拦截器
 */
public class IdempotenceHandler implements HandlerInterceptor {

    @Autowired private TokenService tokenService;

    /**
     * 1、拦截所有请求
     * @param request: 请求对象
     * @param response: 响应对象
     * @param handler: 方法映射
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1、判断方法是不是 HandlerMethod 方法 不是就放行不走 过滤器
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handler1 = (HandlerMethod) handler;
        Method method = handler1.getMethod();
        ApiIdempotence annotation = method.getAnnotation(ApiIdempotence.class);
        // 2、存在ApiIdempotence注解 校验幂等性
        if (annotation != null) {
            tokenService.checkToken(request);
        }
        return true;
    }
}
