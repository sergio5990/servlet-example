package com.github.sergio5990.servlet.example.dao.config;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.UserDao;
import com.github.sergio5990.servlet.example.dao.impl.DefaultAuthUserDao;
import com.github.sergio5990.servlet.example.dao.impl.DefaultUserDao;
import com.github.sergio5990.servlet.example.dao.repository.AuthUserRepository;
import com.github.sergio5990.servlet.example.dao.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.github.sergio5990.servlet.example.dao.repository")
public class DaoConfig {

    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public AuthUserDao authUserDao() {
        return new DefaultAuthUserDao(authUserRepository);
    }

    @Bean
    public UserDao userDao() {
        return new DefaultUserDao(userRepository);
    }
}
