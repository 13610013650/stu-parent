package com.stu.valied.controller;


import com.stu.valied.bean.TestParam;
import com.stu.valied.excption.AddGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/valied",method = RequestMethod.POST)
    public String test(@RequestBody @Validated({AddGroup.class}) TestParam param){
        return "success";
    }

}
