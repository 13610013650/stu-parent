package com.stu.thread.controller;


import com.stu.thread.task.Task;
import com.stu.thread.task.TaskPool;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "test/")
public class TestController {


    @RequestMapping(value = "task/{taskId}", method = RequestMethod.GET)
    public String task(@PathVariable(value = "taskId") String taskId) {
        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskName("任务名称1");
        task.setTaskParam("33");
        TaskPool.getInstance().addTask(task);
        return "SUCCESS";
    }
}
