package com.stu.module.timingtask.handler;

import com.stu.module.timingtask.service.impl.ServiceC;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: eem-services
 * @description: ${}
 * @author: Mr.Zhang
 * @create: 2019-11-16 20:11
 **/
@Component
@Slf4j
public class JobC implements Job {

    @Autowired
    private ServiceC serviceC;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("job C start ...");
        serviceC.sayHello();
        log.info("job C end ...");
    }
}
