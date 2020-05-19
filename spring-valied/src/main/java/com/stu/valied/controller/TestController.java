package com.stu.valied.controller;


import com.stu.valied.bean.TestParam;
import com.stu.valied.bean.TestParam2;
import com.stu.valied.excption.AddGroup;
import com.stu.valied.service.TestService;
import com.stu.valied.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/test")
public class TestController {


    @Autowired
    private TestService testService;

    @RequestMapping(value = "/valid",method = RequestMethod.POST)
    public R test(@RequestBody @Validated({AddGroup.class}) TestParam param){
        return R.success("success");
    }


    @RequestMapping(value = "/valid2",method = RequestMethod.POST)
    public R  test2(@RequestBody @Valid TestParam2 param){
        String s = testService.sayHello();
        return R.success(s);
    }



}
