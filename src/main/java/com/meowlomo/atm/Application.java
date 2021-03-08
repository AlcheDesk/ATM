package com.meowlomo.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {
        // main configuration classes
        "com.meowlomo.atm.config",
        // main configuration http
        "com.meowlomo.atm.config.http",
        // spring service classes
        "com.meowlomo.atm.core",
        // notification service
        "com.meowlomo.atm.quartz",
        // Scheduled tasks
        "com.meowlomo.atm.notification",
        // external ems service
        "com.meowlomo.atm.external.ems.service",
        // listener classes
        "com.meowlomo.atm.listener",
        // Spring MVC Controller
        "com.meowlomo.atm",
        // util classes
        "com.meowlomo.atm.util",
        // jersey REST and provider classes
        "com.meowlomo.atm.core.resource",
        // file export package
        "com.meowlomo.atm.core.service.exportor",
        // security package
        "com.meowlomo.atm.security",
        // report package
        "com.meowlomo.atm.report",
        // report package
        "com.meowlomo.atm.typehandler"})
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}