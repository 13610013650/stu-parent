 package com.java.clazz.aware;

 import org.springframework.context.ApplicationContext;
 import org.springframework.context.ApplicationEvent;

 /**
  * @ProjectName: stu-parent
  * @Package: com.java.clazz.aware
  * @ClassName: ApplicationEvenPublisher
  * @Author: ZhangSheng
  * @Description: ${description}
  * @Date: 2020/1/19 10:36
  * @Version: 1.0
  */
public class TaskEven extends ApplicationEvent {

     private ApplicationContext applicationContext;

     public TaskEven(Object source) {
         super(source);
     }

}
