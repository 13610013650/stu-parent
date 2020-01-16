 package com.hello.starter;

 import com.stu.check.checker.Check;
 import com.stu.check.test.Param;
 import jdk.nashorn.internal.objects.annotations.Getter;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.hello.starter
 * @ClassName: TestController
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/16 10:32
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {


     @GetMapping("/get")
     @Check({"name,age"})
     public String get(Param param){
         return "SUCCESS";
     }

}
