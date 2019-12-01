package com.github.sergio5990.servlet.example.web.spring;

import com.github.sergio5990.servlet.example.service.config.ServiceConfig;
import com.github.sergio5990.servlet.example.web.controller.LoginController;
import com.github.sergio5990.servlet.example.web.controller.LogoutController;
import com.github.sergio5990.servlet.example.web.controller.StudentsController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public LoginController loginController(){
        return new LoginController(serviceConfig.securityService());
    }

    @Bean
    public LogoutController logoutController(){
        return new LogoutController(serviceConfig.securityService());
    }

    @Bean
    public StudentsController studentsController(){
        return new StudentsController(serviceConfig.userService());
    }

    @Bean
    public UrlBasedViewResolver tilesViewResolver(){
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(){
        final TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
        return tilesConfigurer;
    }
}
