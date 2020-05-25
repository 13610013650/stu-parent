package com.stu.moudle.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 获取token的接口
 */
@RestController
@RequestMapping("/api")
public class TokenController {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String get() {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, token);
        return token;
    }


}
