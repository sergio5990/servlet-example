package com.github.sergio5990.servlet.example.service.config;

import com.github.sergio5990.servlet.example.dao.config.DaoConfig;
import com.github.sergio5990.servlet.example.service.SecurityService;
import com.github.sergio5990.servlet.example.service.UserService;
import com.github.sergio5990.servlet.example.service.impl.DefaultSecurityService;
import com.github.sergio5990.servlet.example.service.impl.DefaultUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    public SecurityService securityService(){
        return new DefaultSecurityService(daoConfig.authUserDao());
    }

    @Bean
    public UserService userService(){
        return new DefaultUserService(daoConfig.userDao());
    }
}
