 package com.stu.wapper;

 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.boot.web.servlet.ServletComponentScan;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.wapper
 * @ClassName: WapperApplication
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/20 16:36
 * @Version: 1.0
 */
@ServletComponentScan
@SpringBootApplication
public class WapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WapperApplication.class,args);
    }

}
