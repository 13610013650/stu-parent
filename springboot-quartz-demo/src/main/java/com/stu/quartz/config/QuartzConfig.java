 package com.stu.quartz.config;
 import org.quartz.Scheduler;
 import org.quartz.ee.servlet.QuartzInitializerListener;
 import org.springframework.beans.factory.config.PropertiesFactoryBean;
 import org.springframework.context.annotation.Bean;
 import org.springframework.core.io.ClassPathResource;
 import org.springframework.scheduling.quartz.SchedulerFactoryBean;

 import java.io.IOException;
 import java.util.Properties;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.quartz.config
 * @ClassName: QuartzConfig
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/18 15:43
 * @Version: 1.0
 */
//@Configuration
public class QuartzConfig {

     @Bean
     public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
         SchedulerFactoryBean factory = new SchedulerFactoryBean();
         factory.setQuartzProperties(quartzProperties());
         return factory;
     }

     @Bean
     public Properties quartzProperties() throws IOException {
         PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
         propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
         //在quartz.properties中的属性被读取并注入后再初始化对象
         propertiesFactoryBean.afterPropertiesSet();
         return propertiesFactoryBean.getObject();
     }

     @Bean
     public QuartzInitializerListener executorListener() {
         return new QuartzInitializerListener();
     }

     @Bean
     public Scheduler scheduler() throws IOException {
         return schedulerFactoryBean().getScheduler();
     }


}
