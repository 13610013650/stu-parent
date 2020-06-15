package com.stu.thread.task;

import java.util.concurrent.LinkedBlockingQueue;

public class TaskPool<T extends BaseTask> implements TaskPoolExcutor<BaseTask> {

    private static final TaskPool<BaseTask> TASK_POOL = new TaskPool<>();

    private LinkedBlockingQueue<T> TASK_QUEUE = new LinkedBlockingQueue<>();


    private TaskPool() {

    }

    public static TaskPool<BaseTask> getInstance() {
        return TASK_POOL;
    }


    @Override
    public void addTask(BaseTask baseTask) {
        TASK_QUEUE.add((T) baseTask);
    }

    @Override
    public void excutTask() {
        new Thread(() -> {
            while (true) {
                try {
                    BaseTask task = TASK_QUEUE.take();
                    task.excutTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
