package com.github.sergio5990.servlet.example.web.servlet;

import com.github.sergio5990.servlet.example.model.User;
import com.github.sergio5990.servlet.example.service.UserService;
import com.github.sergio5990.servlet.example.service.impl.DefaultUserService;
import com.github.sergio5990.servlet.example.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/student")
public class StudentsServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(StudentsServlet.class);
    private UserService userService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        List<User> students = userService.getStudents();
        rq.setAttribute("students", students);
        WebUtils.forward("student", rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        String firstName = rq.getParameter("firstName");
        String lastName = rq.getParameter("lastName");
        String phone = rq.getParameter("phone");
        String email = rq.getParameter("email");
        Long id = userService.saveStudent(new User(null, firstName, lastName, phone, email));
        log.info("user created:{} at {}", id, LocalDateTime.now());

        WebUtils.redirect("/student", rq, rs);
    }
}
