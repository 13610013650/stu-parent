package com.stu.module.springsecurity.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: stu-parent
 * @description: ${}
 * @author: Mr.Zhang
 * @create: 2019-11-07 16:39
 **/
@RestController
public class TestController {

    @GetMapping("/get")
    public String get(){
        return "hello world!";
    }

}
