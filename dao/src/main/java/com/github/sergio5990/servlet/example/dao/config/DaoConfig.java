package com.github.sergio5990.servlet.example.dao.config;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.UserDao;
import com.github.sergio5990.servlet.example.dao.impl.DefaultAuthUserDao;
import com.github.sergio5990.servlet.example.dao.impl.DefaultUserDao;
import com.github.sergio5990.servlet.example.dao.repository.AuthUserRepository;
import com.github.sergio5990.servlet.example.dao.repository.UserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.github.sergio5990.servlet.example.dao.repository")
@EntityScan("com.github.sergio5990.servlet.example.dao.entity")
public class DaoConfig {

    private final AuthUserRepository authUserRepository;
    private final UserRepository userRepository;

    public DaoConfig(AuthUserRepository authUserRepository, UserRepository userRepository) {
        this.authUserRepository = authUserRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public AuthUserDao authUserDao() {
        return new DefaultAuthUserDao(authUserRepository);
    }

    @Bean
    public UserDao userDao() {
        return new DefaultUserDao(userRepository);
    }
}
