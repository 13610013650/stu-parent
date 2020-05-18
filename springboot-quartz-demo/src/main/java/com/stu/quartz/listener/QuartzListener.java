 package com.stu.quartz.listener;

 import org.quartz.Scheduler;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.ApplicationEvent;
 import org.springframework.context.ApplicationListener;

 /**
  * @ProjectName: stu-parent
  * @Package: com.stu.quartz.listener
  * @ClassName: QuartzListener
  * @Author: ZhangSheng
  * @Description: ${description}
  * @Date: 2020/1/18 15:37
  * @Version: 1.0
  */
public class QuartzListener implements ApplicationListener{


     @Autowired
     private Scheduler scheduler;

     @Override
     public void onApplicationEvent(ApplicationEvent event) {
         System.out.println("启动中。。。。。。。。");
     }

 }
