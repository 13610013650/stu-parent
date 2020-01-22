 package com.stu.quartz.simpletask;
 import org.quartz.Job;
 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;

 import java.time.LocalDate;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.quartz.task
 * @ClassName: TestTask
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/18 14:25
 * @Version: 1.0
 */
public class TestJob implements Job{

     @Override
     public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
         System.out.println("一个简单的定时任务正在执行:"+ LocalDate.now());
     }
 }
