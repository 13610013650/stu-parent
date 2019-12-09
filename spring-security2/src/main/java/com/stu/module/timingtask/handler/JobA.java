package com.stu.module.timingtask.handler;

import com.stu.module.timingtask.service.impl.ServiceA;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: eem-services
 * @description: ${}
 * @author: Mr.ZhangDESCRIPTION
 * @create: 2019-11-16 20:08
 **/
@Component
@Slf4j
public class JobA implements Job {

    @Autowired
    private ServiceA serviceA ;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("job A start ...");
        serviceA.sayHello();
        log.info("job A end ...");
    }

}
