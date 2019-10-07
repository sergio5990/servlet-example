package com.github.sergio5990.servlet.example.dao;

import com.github.sergio5990.servlet.example.model.AuthUser;

public interface AuthUserDao {

    AuthUser getByLogin(String login);

    long saveAuthUser(AuthUser user);
}
