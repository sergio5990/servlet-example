package com.github.sergio5990.servlet.example.web.servlet;

import com.github.sergio5990.servlet.example.model.User;
import com.github.sergio5990.servlet.example.service.UserService;
import com.github.sergio5990.servlet.example.service.impl.DefaultUserService;
import com.github.sergio5990.servlet.example.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping
public class StudentsServlet {
    private static final Logger log = LoggerFactory.getLogger(StudentsServlet.class);
    @Autowired
    private UserService userService;

    @GetMapping("/student")
    public String doGet(HttpServletRequest rq) {
        List<User> students = userService.getStudents();
        rq.setAttribute("students", students);
        return "/student";
    }

    @PostMapping("/student")
    public String doPost(HttpServletRequest rq) {
        String firstName = rq.getParameter("firstName");
        String lastName = rq.getParameter("lastName");
        String phone = rq.getParameter("phone");
        String email = rq.getParameter("email");
        Long id = userService.saveStudent(new User(null, firstName, lastName, phone, email));
        log.info("user created:{} at {}", id, LocalDateTime.now());

        return "redirect:/student";
    }
}
