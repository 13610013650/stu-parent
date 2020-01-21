 package com.java.clazz.controller;

 import com.java.clazz.service.MessageService;
 import com.java.clazz.utils.EnvironmentUtil;
 import com.java.clazz.utils.TestUtil;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 import java.util.List;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.clazz.controller
 * @ClassName: TestController
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/19 0:48
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("get")
    public List<MessageService.User> getAware(){
        return TestUtil.getMessages();
    }

    @GetMapping("getProperty")
    public String getProperty(){
        return EnvironmentUtil.getEnvironment()
                .getProperty("server.port");
    }

    @GetMapping("getProperty2/{key}")
    public String getProperty2(@PathVariable("key") String key){
        return EnvironmentUtil.getProperty(key);
    }

}
