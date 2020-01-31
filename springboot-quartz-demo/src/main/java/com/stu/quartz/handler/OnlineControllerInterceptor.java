 package com.stu.quartz.handler;

 import org.springframework.lang.Nullable;
 import org.springframework.stereotype.Component;
 import org.springframework.web.servlet.HandlerInterceptor;
 import org.springframework.web.servlet.ModelAndView;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.quartz.handler
 * @ClassName: OnlineController
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/25 15:36
 * @Version: 1.0
 */
@Component
public class OnlineControllerInterceptor implements HandlerInterceptor{

     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
             throws Exception {
         String id = request.getSession().getId();
         System.out.println("---进入拦截 session---:"+id);
         return true;
     }

    @Override
     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                             @Nullable ModelAndView modelAndView) throws Exception {
        String id = request.getSession().getId();
        System.out.println("---请求处理 session---:"+id);
    }
    
}
