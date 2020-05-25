package com.stu.moudle.controller;

import com.stu.moudle.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取token的接口
 */
@RestController
@RequestMapping("/api")
public class TokenController {


    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String get() {
        return tokenService.createToken();
    }


}
