package com.github.sergio5990.servlet.example.service.impl;

import com.github.sergio5990.servlet.example.dao.impl.DefaultUserDao;
import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.model.User;
import com.github.sergio5990.servlet.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserService implements UserService {
    @Autowired
    private DefaultUserDao defaultUserDao;

    @Override
    public List<User> getStudents() {
        return defaultUserDao.getStudents();
    }

    @Override
    public Long saveStudent(User user) {
        return defaultUserDao.save(user);
    }
}
