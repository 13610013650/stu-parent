 package com.stu.wapper.config;
 import com.stu.wapper.LoginInterceptor;
 import com.stu.wapper.TestFilter;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.HandlerInterceptor;
 import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.wapper.config
 * @ClassName: WebAppConfigurer
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/20 17:30
 * @Version: 1.0
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

     @Override
     public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
     }

}
