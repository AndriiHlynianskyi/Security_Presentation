package com.ahl.x509;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.core.env.AbstractEnvironment;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication(scanBasePackages = {"com.ahl.x509", "com.ahl.mvc", "com.ahl.persistence, com.ahl.common"})
public class X509Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "x509");
        SpringApplication.run(X509Application.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "x509");
        super.onStartup(servletContext);
    }
}
