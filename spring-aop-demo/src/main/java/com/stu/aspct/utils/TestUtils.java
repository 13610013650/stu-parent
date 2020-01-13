 package com.stu.aspct.utils;

 import com.stu.aspct.service.TestService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.aspct
 * @ClassName: Utils
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/13 10:44
 * @Version: 1.0
 */
@Component
public class TestUtils {

   private TestCommon common;

   @Autowired
   public  void setTestService(TestCommon common){
        this.common = common;
   }


   public  String coverFormat(String args){
       return common.parseString(args);
   }
}
