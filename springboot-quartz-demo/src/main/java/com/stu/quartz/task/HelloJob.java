 package com.stu.quartz.task;

 import lombok.extern.slf4j.Slf4j;
 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;

 import java.time.LocalDateTime;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.quartz.task
 * @ClassName: HelloJob
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/18 17:30
 * @Version: 1.0
 */
@Slf4j
public class HelloJob implements BaseJob{

     @Override
     public void execute(JobExecutionContext var1) throws JobExecutionException {
        log.info("hello job 正在执行,{}", LocalDateTime.now());
     }
 }
