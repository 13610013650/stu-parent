package com.stu.moudle.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public final class RedisCacheManagement {

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisCacheManagement(RedisTemplate<String, Object> redisTemplate) {
        RedisCacheManagement.redisTemplate = redisTemplate;
    }

    public static void saveStr(String k, Object v) {
        redisTemplate.opsForValue().set(k, v);
    }

    public static void saveStr(String k, Object v, long millions) {
        redisTemplate.opsForValue().set(k, v, millions, TimeUnit.SECONDS);
    }

    public static Object getStr(String k) {
        return redisTemplate.opsForValue().get(k);
    }

}
