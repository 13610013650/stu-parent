 package com.stu.wapper;

 import com.stu.wapper.bean.User;
 import com.stu.wapper.util.UserCacheUtils;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.lang.Nullable;
 import org.springframework.stereotype.Component;
 import org.springframework.web.HttpRequestHandler;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.method.HandlerMethod;
 import org.springframework.web.servlet.HandlerInterceptor;
 import org.springframework.web.servlet.ModelAndView;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.lang.annotation.Annotation;
 import java.util.Enumeration;
 import java.util.UUID;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.wapper
 * @ClassName: LoginInterceptor
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/20 17:17
 * @Version: 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

     private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
             throws Exception {
        logger.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                             @Nullable ModelAndView modelAndView) throws Exception {
         logger.info("--------------处理请求时视图的处理操作---------------");
         logger.info("getContextPath:"+request.getContextPath());
         logger.info("getRequestURI:"+request.getRequestURI());
         logger.info("getParameter:"+request.getParameter("param"));
         logger.info("authType:"+request.getAuthType());
         logger.info("Lock-Token:"+request.getHeader("Lock-Token"));
         Enumeration<String> headerNames = request.getHeaderNames();
         while(headerNames.hasMoreElements()){
             logger.info("headerNames:" + headerNames.nextElement());
         }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        GetMapping annotation = handlerMethod.getMethod().getAnnotation(GetMapping.class);
        if (annotation !=null ){
            logger.info("存在 GettMapping 注解.");
        }
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setAge(26);
        user.setPassword("1");
        user.setUserName("ZhangSan");
        UserCacheUtils.set(user);
     }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                  @Nullable Exception ex) throws Exception {
         logger.info("---------------视图渲染之后的操作--------------------------");
//        UserCacheUtils.clear();
        User user = UserCacheUtils.get();
        logger.info("获取缓存中的USER："+user.toString());
    }
}
