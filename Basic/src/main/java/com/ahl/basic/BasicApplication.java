package com.ahl.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.core.env.AbstractEnvironment;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication(scanBasePackages = {"com.ahl.basic", "com.ahl.mvc"})
public class BasicApplication {
	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}
}
