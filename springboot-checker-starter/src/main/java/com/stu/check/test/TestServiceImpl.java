 package com.stu.check.test;

 import com.stu.check.checker.Check;
 import org.springframework.stereotype.Service;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.check.test
 * @ClassName: TestServiceImpl
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/15 16:59
 * @Version: 1.0
 */
 @Service
public class TestServiceImpl implements TestService{

    @Override
    public String test(Param param) {
        return "SUCCESS";
    }
}
