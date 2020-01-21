 package com.java.clazz.utils;

 import org.springframework.context.EnvironmentAware;
 import org.springframework.core.env.Environment;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.clazz.utils
 * @ClassName: EnvironmentUtil
 * @Author: ZhangSheng
 * @Description:
 * @Date: 2020/1/19 15:24
 * @Version: 1.0
 */
@Component
public final class EnvironmentUtil implements EnvironmentAware {

     private static Environment environment;

     @Override
     public void setEnvironment(Environment environment) {
         EnvironmentUtil.environment = environment;
     }

     public static Environment getEnvironment(){
         return environment;
     }

     public static String getProperty(String key){
         return getEnvironment().getProperty(key);
     }

     public static <T> T getProperty(String key,Class<T> clazz){
         return getEnvironment().getProperty(key,clazz);
     }

 }
