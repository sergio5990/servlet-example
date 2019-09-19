package com.github.sergio5990.servlet.example.dao;

import com.github.sergio5990.servlet.example.model.User;

import java.util.List;

public interface UserDao {
    List<User> getStudents();

    String save(User user);
}
