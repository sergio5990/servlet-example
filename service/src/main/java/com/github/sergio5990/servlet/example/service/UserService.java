package com.github.sergio5990.servlet.example.service;

import com.github.sergio5990.servlet.example.model.User;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {
    List<User> getStudents();

    @PreAuthorize("hasAuthority('ROLE_PROFESSOR')")
    Long saveStudent(User user);
}
