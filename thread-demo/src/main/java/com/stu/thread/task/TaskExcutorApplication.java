package com.stu.thread.task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class TaskExcutorApplication implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        long timestamp = contextRefreshedEvent.getTimestamp();
        System.out.println("=============启动应用==================");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TaskPool.getInstance().excutTask();
        System.out.println("=============启动应用后==================");
        System.out.println("共执行时长:" + (contextRefreshedEvent.getTimestamp() - timestamp) + "ms");
    }
}
