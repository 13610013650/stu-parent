 package com.java.clazz.service;

 import org.springframework.stereotype.Service;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.java.clazz.service
 * @ClassName: TestService
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/19 0:15
 * @Version: 1.0
 */
@Service("testService")
public class TestService {

    public void say(){
        System.out.println("hello test aware interface");
    }
}
