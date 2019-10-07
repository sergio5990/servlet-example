package com.github.sergio5990.servlet.example.service.impl;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.DataSource;
import com.github.sergio5990.servlet.example.dao.impl.DefaultAuthUserDao;
import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.service.SecurityService;

public class DefaultSecurityService implements SecurityService {

    private static class SingletonHolder {
        static final SecurityService HOLDER_INSTANCE = new DefaultSecurityService();
    }
    public static SecurityService getInstance() {
        return DefaultSecurityService.SingletonHolder.HOLDER_INSTANCE;
    }

    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();

    public AuthUser login(String login, String password) {
        AuthUser user = authUserDao.getByLogin(login);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
