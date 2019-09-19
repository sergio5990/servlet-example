package com.github.sergio5990.servlet.example.service;

import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.service.impl.DefaultSecurityService;

public interface SecurityService {
    AuthUser login(String login, String password);
}
