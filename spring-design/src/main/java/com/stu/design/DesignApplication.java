 package com.stu.design;

 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.scheduling.annotation.EnableAsync;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.design
 * @ClassName: DesignApplication
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/29 1:01
 * @Version: 1.0
 */
@EnableAsync
@SpringBootApplication
public class DesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignApplication.class,args);
    }

}
