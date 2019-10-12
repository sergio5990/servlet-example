package com.github.sergio5990.servlet.example.service.impl;

import com.github.sergio5990.servlet.example.dao.impl.DefaultUserDao;
import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.model.User;
import com.github.sergio5990.servlet.example.service.UserService;

import java.util.List;

public class DefaultUserService implements UserService {

    private static class SingletonHolder {
        static final UserService HOLDER_INSTANCE = new DefaultUserService();
    }

    public static UserService getInstance() {
        return DefaultUserService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public List<User> getStudents() {
        return DefaultUserDao.getInstance().getStudents();
    }

    @Override
    public Long saveStudent(User user) {
        return DefaultUserDao.getInstance().save(user);
    }

    @Override
    public void saveAuthUser(AuthUser authUser) {

    }
}
