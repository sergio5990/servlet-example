package com.github.sergio5990.servlet.example.service;

import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.model.User;

import java.util.List;

public interface UserService {
    List<User> getStudents();

    Long saveStudent(User user);
}
