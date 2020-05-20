package com.stu.moudle.controller;


import com.stu.moudle.bean.TestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RedisTestController {


    @Autowired private KeyGenerator keyGenerator;

    @Autowired private RedisTemplate redisTemplate;


    @PostMapping("/redis")
    public String redis(@RequestBody TestParam param){
        redisTemplate.opsForValue().set("1",param);
        return "SUCCESS";
    }
}
