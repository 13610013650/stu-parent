 package com.stu.design.observer;

 import lombok.extern.slf4j.Slf4j;
 import org.springframework.context.ApplicationListener;
 import org.springframework.scheduling.annotation.Async;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design.observer
 * @ClassName: EmplyeeObserver
 * @Author: ZhangSheng
 * @Description: 员工观察者
 * @Date: 2019/12/29 1:33
 * @Version: 1.0
 */
@Slf4j
@Component
public class EmplyeeObserver implements ApplicationListener<ProjectObservable> {

    @Override
    @Async
    public void onApplicationEvent(ProjectObservable event) {
       log.info("插入项目关联的人员数据...");
    }

 }
