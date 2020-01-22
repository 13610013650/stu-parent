 package com.stu.quartz.simpletask;

 import org.quartz.*;
 import org.quartz.impl.StdSchedulerFactory;

 import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.quartz.task
 * @ClassName: TestMain
 * @Author: ZhangSheng
 * @Description: 一个简单的quartz demo
 * @Date: 2020/1/18 14:27
 * @Version: 1.0
 */
public class TestScheduler {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();



        JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
                .withIdentity("job1","group")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group")
                .startNow()
                .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(6)
                    .withRepeatCount(10))
                .build();

        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();

    }

}
