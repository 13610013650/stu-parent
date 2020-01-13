 package com.stu.aspct.service.impl;

 import com.stu.aspct.service.TestService;
 import com.stu.aspct.utils.TestUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.aspct.service.impl
 * @ClassName: TestServiceImpl
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/13 10:42
 * @Version: 1.0
 */
 @Service
public class TestServiceImpl implements TestService{

     private TestUtils testUtils;

     @Autowired
     public void setTestUtils(TestUtils testUtils) {
         this.testUtils = testUtils;
     }

     @Override
     public String  sayHello(String v1) {
         System.out.println("test service....");
         String format = testUtils.coverFormat(v1);
         return new StringBuffer(format).toString();
     }
 }
