package com.stu.check;

import com.stu.check.controller.EnableDynamicController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamicController
public class SpringbootCheckerStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCheckerStarterApplication.class, args);
	}

}
