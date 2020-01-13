 package com.stu.design.observer;

 import lombok.extern.slf4j.Slf4j;
 import org.springframework.context.ApplicationListener;
 import org.springframework.scheduling.annotation.Async;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.observer
 * @ClassName: ProjectObserver
 * @Author: ZhangSheng
 * @Description: 项目创建观察者
 * @Date: 2019/12/29 1:43
 * @Version: 1.0
 */
 @Slf4j
 @Component
public class ProjectObserver implements ApplicationListener<ProjectObservable>{

     @Override
     @Async
     public void onApplicationEvent(ProjectObservable event) {
         log.info("项目表中插入数据..."+ event.getProjectName());
     }

 }
