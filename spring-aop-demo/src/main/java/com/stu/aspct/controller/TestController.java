 package com.stu.aspct.controller;

 import com.stu.aspct.annotation.Log;
 import com.stu.aspct.service.TestService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RestController;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.aspct.controller
 * @ClassName: TestController
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/13 10:39
 * @Version: 1.0
 */
@RestController
public class TestController {

    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test/{test}")
    @Log(param ="zhangsan")
    public String  test(@PathVariable("test") String value){
        return  testService.sayHello(value);
    }


}
