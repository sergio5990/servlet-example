package com.github.sergio5990.servlet.example.service.impl;

import com.github.sergio5990.servlet.example.dao.UserDao;
import com.github.sergio5990.servlet.example.dao.impl.DefaultUserDao;
import com.github.sergio5990.servlet.example.model.User;
import com.github.sergio5990.servlet.example.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultUserService implements UserService {
    private final UserDao defaultUserDao;

    public DefaultUserService(UserDao defaultUserDao) {
        this.defaultUserDao = defaultUserDao;
    }

    @Override
    @Transactional
    public List<User> getStudents() {
        return defaultUserDao.getStudents();
    }

    @Override
    @Transactional
    public Long saveStudent(User user) {
        return defaultUserDao.save(user);
    }
}
