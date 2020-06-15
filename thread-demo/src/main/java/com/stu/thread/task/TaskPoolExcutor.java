package com.stu.thread.task;


public interface TaskPoolExcutor<T extends BaseTask> {

    void addTask(T t);

    void excutTask();

}
