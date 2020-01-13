 package com.stu.aspct.utils;

 import org.springframework.stereotype.Component;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.aspct.utils
 * @ClassName: TestCommon
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2020/1/13 10:56
 * @Version: 1.0
 */
@Component
public class TestCommon {


    public String parseString(String v1){
        return v1.toUpperCase();
    }
}
