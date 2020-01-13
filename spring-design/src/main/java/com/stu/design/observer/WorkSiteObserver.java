 package com.stu.design.observer;

 import lombok.extern.slf4j.Slf4j;
 import org.springframework.context.ApplicationListener;
 import org.springframework.scheduling.annotation.Async;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.observer
 * @ClassName: WorkSiteObserver
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/29 1:45
 * @Version: 1.0
 */
 @Component
 @Slf4j
public class WorkSiteObserver implements ApplicationListener<ProjectObservable> {

     @Override
     @Async
     public void onApplicationEvent(ProjectObservable event) {
        log.info("插入项目关联的工地...");
     }
 }
