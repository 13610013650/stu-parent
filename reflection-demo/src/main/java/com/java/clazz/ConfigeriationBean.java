 package com.java.clazz;

 import org.springframework.beans.factory.InitializingBean;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.clazz
 * @ClassName: ConfigeriationBean
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/19 15:56
 * @Version: 1.0
 */
@Component
public class ConfigeriationBean implements InitializingBean {

     static {
         System.out.println("static.....1");
     }

     @Override
     public void afterPropertiesSet() throws Exception {
         System.out.println("afterPropertiesSet .....");
     }

     ConfigeriationBean(){
         System.out.println("ConfigeriationBean's Constructor");
     }

     static {
         System.out.println("static.....2");
     }

     public void test(){
         System.out.println("test");
     }

 }
