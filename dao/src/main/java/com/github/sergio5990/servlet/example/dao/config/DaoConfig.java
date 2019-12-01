package com.github.sergio5990.servlet.example.dao.config;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.UserDao;
import com.github.sergio5990.servlet.example.dao.impl.DefaultAuthUserDao;
import com.github.sergio5990.servlet.example.dao.impl.DefaultUserDao;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
public class DaoConfig {

    private SessionFactory sessionFactory;

    public DaoConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Bean
    public AuthUserDao authUserDao() {
        return new DefaultAuthUserDao(sessionFactory);
    }

    @Bean
    public UserDao userDao() {
        return new DefaultUserDao(sessionFactory);
    }
}
