 package com.stu.check.test;

 import com.stu.check.checker.Check;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.check
 * @ClassName: test
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/15 16:15
 * @Version: 1.0
 */
@RestController
public class TestController {

    @Autowired private TestService testService;

    @RequestMapping("get")
    @Check({"name","age"})
    public String get(Param param){
        return testService.test(param);
    }
}
