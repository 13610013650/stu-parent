 package com.stu.activiti;

 import org.activiti.spring.boot.SecurityAutoConfiguration;
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.activiti
 * @ClassName: ActivitiApplication
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/31 11:14
 * @Version: 1.0
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class,args);
    }
}
