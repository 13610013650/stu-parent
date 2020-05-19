package com.stu.valied.service.impl;

import com.stu.valied.service.TestService;
import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {


    @Override
    public String sayHello() {
        int i = 2/0;
        return "Hello";
    }
}
