package com.stu.valied.controller;


import com.stu.valied.bean.TestParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/valied")
    public String test(@RequestBody @Validated TestParam param){
        return "success";
    }

}
