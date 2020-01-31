 package com.stu.quartz.task;

 import org.quartz.Job;
 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.quartz.task
 * @ClassName: BaseJob
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/18 17:28
 * @Version: 1.0
 */
public interface BaseJob extends Job{

     @Override
     void execute(JobExecutionContext var1) throws JobExecutionException;

}
