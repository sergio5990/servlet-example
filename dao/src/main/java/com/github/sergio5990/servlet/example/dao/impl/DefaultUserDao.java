package com.github.sergio5990.servlet.example.dao.impl;

import com.github.sergio5990.servlet.example.dao.UserDao;
import com.github.sergio5990.servlet.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao {
    private static volatile UserDao instance;

    public DefaultUserDao() {
        this.users = new ArrayList<>();
    }

    public static UserDao getInstance() {
        UserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultUserDao();
                }
            }
        }
        return localInstance;
    }

    private List<User> users;

    @Override
    public List<User> getStudents() {
        return users;
    }

    @Override
    public String save(User user) {
        users.add(user);
        return user.getId();
    }
}
