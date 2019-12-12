package com.github.sergio5990.servlet.example;

import com.github.sergio5990.servlet.example.dao.config.DaoConfig;
import com.github.sergio5990.servlet.example.service.config.ServiceConfig;
import com.github.sergio5990.servlet.example.web.config.WebConfig;
import com.github.sergio5990.servlet.example.web.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import({DaoConfig.class, ServiceConfig.class, WebConfig.class, WebSecurityConfig.class})
public class AppRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
    }

}
