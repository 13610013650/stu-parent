package com.hello.starter;

import com.stu.check.controller.EnableDynamicController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamicController
public class SpringbootHelloStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHelloStarterApplication.class, args);
	}

}
