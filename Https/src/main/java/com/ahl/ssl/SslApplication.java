package com.ahl.ssl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.core.env.AbstractEnvironment;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication(scanBasePackages = {"com.ahl.ssl", "com.ahl.mvc", "com.ahl.persistence, com.ahl.common"})
public class SslApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "ssl");
        SpringApplication.run(SslApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "ssl");
        super.onStartup(servletContext);
    }
}
