package com.github.sergio5990.servlet.example.dao;

import com.github.sergio5990.servlet.example.model.AuthUser;

public interface AuthUserDao {

    AuthUser getByLogin(String login);

    AuthUser get(Long id);

    long saveAuthUser(AuthUser user);

    void updatePassword(Long authUserId, String newPassword);
}
