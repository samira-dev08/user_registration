package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RegisterUserApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RegisterUserApplication.class, args);
	}

}
