package com.stu.thread.task;

public class Test_task {

    public static void main(String[] args) throws InterruptedException {

        TaskPool instance = TaskPool.getInstance();

        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setTaskId(String.valueOf(i));
            task.setTaskName("task" + i);
            task.setTaskParam("参数" + i);
            instance.addTask(task);
        }

        instance.excutTask();

    }
}
