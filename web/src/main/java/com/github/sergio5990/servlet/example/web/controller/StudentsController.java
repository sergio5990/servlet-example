package com.github.sergio5990.servlet.example.web.controller;

import com.github.sergio5990.servlet.example.model.User;
import com.github.sergio5990.servlet.example.service.UserService;
import com.github.sergio5990.servlet.example.web.rq.CreateStudentRq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentsController {
    private static final Logger log = LoggerFactory.getLogger(StudentsController.class);
    private final UserService userService;

    public StudentsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String get(HttpServletRequest rq) {
        List<User> students = userService.getStudents();
        rq.setAttribute("students", students);
        return "student";
    }

    @PostMapping()
    public String create(CreateStudentRq rq) {
        String firstName = rq.getFirstName();
        String lastName = rq.getLastName();
        String phone = rq.getPhone();
        String email = rq.getEmail();
        Long id = userService.saveStudent(new User(null, firstName, lastName, phone, email));
        log.info("user created:{} at {}", id, LocalDateTime.now());

        return "redirect:/student";
    }
}
