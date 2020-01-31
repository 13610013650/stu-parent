 package com.stu.quartz.config;

 import com.stu.quartz.handler.OnlineControllerInterceptor;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.quartz.config
 * @ClassName: WebAppAdapater
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/25 15:44
 * @Version: 1.0
 */
@Configuration
public class WebAppAdapater extends WebMvcConfigurationSupport {

    private OnlineControllerInterceptor interceptor;

    @Autowired
    WebAppAdapater(OnlineControllerInterceptor interceptor){
        this.interceptor = interceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/quartz/test/**");
    }
}
