package com.java.clazz;

import com.java.clazz.aware.ApplicationContextUtil;
import com.java.clazz.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ReflectionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReflectionDemoApplication.class, args);
	}

	@GetMapping("get")
	public String  get(){
        TestService bean = ApplicationContextUtil.getBean(TestService.class);
        bean.say();
        return "SUCCESS";
    }

}
