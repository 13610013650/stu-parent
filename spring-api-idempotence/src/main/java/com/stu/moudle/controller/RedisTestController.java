package com.stu.moudle.controller;

import com.stu.moudle.annotation.ApiIdempotence;
import com.stu.moudle.bean.TestParam;
import com.stu.moudle.utils.RedisCacheManagement;
import org.springframework.web.bind.annotation.*;

/**
 * 接口幂等性功能测试。
 */
@RestController
@RequestMapping(value = "/api/redis")
public class RedisTestController {


    @PostMapping("/set/{key}")
    @ResponseBody
    @ApiIdempotence
    public String redis(@RequestBody TestParam param, @PathVariable String key) {
        RedisCacheManagement.saveStr(key, param, 1000);
        return "SUCCESS";
    }

    @RequestMapping(value = "/get/{key}",method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable String key) {
        return RedisCacheManagement.getStr(key);
    }

}
