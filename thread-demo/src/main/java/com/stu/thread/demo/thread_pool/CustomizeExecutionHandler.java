package com.stu.thread.demo.thread_pool;

public interface CustomizeExecutionHandler {

    void rejectedExection(Runnable task,ThreadPool exectPool);

}
