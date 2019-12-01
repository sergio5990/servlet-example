package com.github.sergio5990.servlet.example.service.impl;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.service.SecurityService;
import org.springframework.transaction.annotation.Transactional;

public class DefaultSecurityService implements SecurityService {

    private final AuthUserDao authUserDao;

    public DefaultSecurityService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    @Transactional
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

    @Override
    @Transactional
    public void updatePassword(Long authUserId, String newPassword) {
        authUserDao.updatePassword(authUserId, newPassword);
    }
}
