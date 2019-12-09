package com.stu.module.timingtask.manager;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 * @program: eem-services
 * @description: ${}
 * @author: Mr.Zhang
 * @create: 2019-11-17 23:20
 **/
public interface JobManager {

    void startJob() throws SchedulerException;

    String  getJobInfo(String name, String group) throws SchedulerException;

    boolean updateJob(String name, String group, String cron) throws SchedulerException;

    void recoverJob(String name, String group) throws SchedulerException;

    void stopJob(String name, String group)throws SchedulerException;

    void deleteJob(String name, String group) throws SchedulerException;

}
