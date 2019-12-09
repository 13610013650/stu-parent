package com.stu.module.timingtask.service.impl;

import com.stu.module.timingtask.service.JobInterface;
import org.springframework.stereotype.Service;

/**
 * @program: eem-services
 * @description: ${}
 * @author: Mr.ZhangDESCRIPTION
 * @create: 2019-11-17 23:12
 **/
@Service
public class ServiceA implements JobInterface {

    @Override
    public void sayHello() {
        System.out.println("service A 开始同步数据...");
    }

}
