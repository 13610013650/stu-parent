package com.stu.thread.task;

import java.io.Serializable;

public class Task extends BaseTask implements Serializable {


    @Override
    public void excutTask() {
        System.out.println("任务已经被执行");
        System.out.println(getTaskId() + ":" + getTaskName());
    }


}
