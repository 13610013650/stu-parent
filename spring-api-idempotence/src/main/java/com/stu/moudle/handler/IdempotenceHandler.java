package com.stu.moudle.handler;


import com.stu.moudle.annotation.ApiIdempotence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 保证接口幂等性拦截器
 */
public class IdempotenceHandler implements HandlerInterceptor {

    private static final String TOKEN_KEY = "token";


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 1、拦截所有请求
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 1、判断方法是不是 HandlerMethod 方法 不是就放行不走 过滤器
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handler1 = (HandlerMethod) handler;
        Method method = handler1.getMethod();
        ApiIdempotence annotation = method.getAnnotation(ApiIdempotence.class);
        // 2、存在ApiIdempotence注解 校验幂等性
        if (annotation != null) {
            // 2.1、请求头中是否存在token 抛出异常
            String token = request.getHeader(TOKEN_KEY);
            if (StringUtils.isEmpty(token)) {
                token = request.getParameter(TOKEN_KEY);
                if (StringUtils.isEmpty(token)) {
                    throw new RuntimeException("api token is empty...");
                }
            }
            if (!redisTemplate.hasKey(token)) {
                throw new RuntimeException("token is nonexistent ...");
            }
            // 3、删除token
            Boolean delete = redisTemplate.delete(token);
            if (!delete) {
                // 删除成功 放行
                throw new RuntimeException("token is nonexistent ...");
            }
        }
        return true;
    }
}
