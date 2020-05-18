 package com.stu.quartz.task;

 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;
 import org.slf4j.helpers.FormattingTuple;
 import org.slf4j.helpers.MessageFormatter;

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

public class HelloJob implements BaseJob{

     @Override
     public void execute(JobExecutionContext var1) throws JobExecutionException {
         FormattingTuple format = MessageFormatter.format("hello job 正在执行,{}", LocalDateTime.now());
         System.out.println(format);
     }

     public static void main(String[] args) {
         FormattingTuple format = MessageFormatter.format("hello job 正在执行,{}", LocalDateTime.now());
         FormattingTuple format2 = MessageFormatter.format("我是中国人，{}", "我骄傲");
         System.out.println(format.getMessage());
         System.out.println(format2.getMessage());
     }

 }
