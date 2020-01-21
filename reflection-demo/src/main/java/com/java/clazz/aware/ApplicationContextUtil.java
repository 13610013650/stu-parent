 package com.java.clazz.aware;

 import org.springframework.beans.BeansException;
 import org.springframework.context.ApplicationContext;
 import org.springframework.context.ApplicationContextAware;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.clazz.aware
 * @ClassName: ApplicationContext
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/19 0:08
 * @Version: 1.0
 */
@Component
public final class ApplicationContextUtil implements ApplicationContextAware {

     private static ApplicationContext applicationContext;

     @Override
     public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         ApplicationContextUtil.applicationContext =applicationContext;
     }

     private static ApplicationContext getApplicationContext(){
         return applicationContext;
     }

     public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
     }

     public static Object getBean(String beanName){
         return getApplicationContext().getBean(beanName);
     }

 }
