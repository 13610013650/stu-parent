package com.stu.moudle.controller;
import com.stu.moudle.annotation.ApiIdempotence;
import com.stu.moudle.bean.TestParam;
import com.stu.moudle.utils.RedisCacheManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis")
public class RedisTestController {


    @Autowired
    private RedisCacheManagement redisCacheManagement;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @PostMapping("/set/{key}")
    @ApiIdempotence
    public String redis(@RequestBody TestParam param, @PathVariable String key) {
        RedisCacheManagement.saveStr(key, param, 1000);
        return "SUCCESS";
    }

    @GetMapping("/get/{key}")
    public TestParam get(@PathVariable String key) {
        return (TestParam)RedisCacheManagement.getStr(key);
    }

    @PostMapping("/trans/{key}")
    public String testTrans(@RequestBody TestParam param) {
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.multi();
        redisTemplate.opsForValue().set("id", param.getId());
        redisTemplate.opsForValue().set("name", param.getName());
        redisTemplate.opsForValue().set("age", param.getAge());
        redisTemplate.opsForValue().set("addr", param.getAddr());
        redisTemplate.exec();
        return "success";
    }

    @GetMapping("/getkey/{key}")
    public Object getkey(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
