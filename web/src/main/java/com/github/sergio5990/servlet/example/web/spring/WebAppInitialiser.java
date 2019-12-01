package com.github.sergio5990.servlet.example.web.spring;

import com.github.sergio5990.servlet.example.dao.config.DaoConfig;
import com.github.sergio5990.servlet.example.service.config.ServiceConfig;
import com.github.sergio5990.servlet.example.web.spring.RootConfig;
import com.github.sergio5990.servlet.example.web.spring.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class, ServiceConfig.class, DaoConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}