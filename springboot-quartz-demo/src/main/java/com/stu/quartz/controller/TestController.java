 package com.stu.quartz.controller;

 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.quartz.controller
 * @ClassName: TestController
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/25 15:25
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("quartz")
    public String test(){
        return "SUCCESS";
    }

    @GetMapping("session")
    public String session(HttpServletRequest request, HttpServletResponse response){
        return request.getSession().getId();
    }
}
