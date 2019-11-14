package com.github.sergio5990.servlet.example.service.impl;

import com.github.sergio5990.servlet.example.dao.AuthUserDao;
import com.github.sergio5990.servlet.example.dao.impl.DefaultAuthUserDao;
import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSecurityService implements SecurityService {

    @Autowired
    private AuthUserDao authUserDao;

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
    public void updatePassword(Long authUserId, String newPassword) {
        authUserDao.updatePassword(authUserId, newPassword);
    }
}
