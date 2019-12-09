package com.stu.module.timingtask.manager;

import com.stu.module.timingtask.handler.JobC;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @program: eem-services
 * @description: ${}
 * @author: Mr.Zhang
 * @create: 2019-11-17 23:28
 **/
@Configuration
public class JobService implements JobManager{


    public static final String JOB1="job1";


    public static final String GROUP1="group1";

    /**默认5秒执行一次*/
    public static final String DEFAULT_CRON="*/5 * * * * ?";

    @Autowired
    private Scheduler scheduler;


    @Override
    public void startJob() throws SchedulerException {
        beginJob(scheduler);
        scheduler.start();
    }

    private void beginJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail  = JobBuilder.newJob(JobC.class).withIdentity(JOB1,GROUP1).build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(DEFAULT_CRON);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(JOB1,GROUP1)
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    @Override
    public String getJobInfo(String name, String group) throws SchedulerException {
        TriggerKey triggerKey=new TriggerKey(name,group);
        CronTrigger cronTrigger= (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s",cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }

    @Override
    public boolean updateJob(String name,String group,String cron) throws SchedulerException {
        Date date=null;
        TriggerKey triggerKey=new TriggerKey(name, group);
        CronTrigger cronTrigger= (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime=cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(cron)){
            CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder.cronSchedule(cron);
            CronTrigger trigger=TriggerBuilder.newTrigger().withIdentity(name,group)
                    .withSchedule(cronScheduleBuilder).build();
            date=scheduler.rescheduleJob(triggerKey,trigger);
        }
        return date !=null;
    }

    @Override
    public void recoverJob(String name,String group) throws SchedulerException {
        JobKey jobKey=new JobKey(name,group);
        JobDetail jobDetail=scheduler.getJobDetail(jobKey);
        if (jobDetail==null) {
            return;
        }
        scheduler.resumeJob(jobKey);

    }

    @Override
    public void stopJob(String name, String group) throws SchedulerException {
        JobKey jobKey=new JobKey(name,group);
        JobDetail jobDetail=scheduler.getJobDetail(jobKey);
        if (jobDetail==null) {
            return;
        }
        scheduler.pauseJob(jobKey);
    }

    @Override
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey=new JobKey(name, group);
        JobDetail jobDetail=scheduler.getJobDetail(jobKey);
        if (jobDetail==null) {
            return;
        }
        scheduler.deleteJob(jobKey);
    }
}

