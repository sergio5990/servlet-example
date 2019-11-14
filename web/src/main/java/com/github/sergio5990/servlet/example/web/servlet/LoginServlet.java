package com.github.sergio5990.servlet.example.web.servlet;

import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.service.SecurityService;
import com.github.sergio5990.servlet.example.service.impl.DefaultSecurityService;
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
import java.io.IOException;

@Controller
@RequestMapping
public class LoginServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    @Autowired
    private SecurityService securityService;

    @GetMapping("/login")
    public String doGet(HttpServletRequest rq) {
        Object authUser = rq.getSession().getAttribute("authUser");
        if (authUser == null) {
            return "login";
        }
        return "redirect:/student";
    }

    @PostMapping("/login")
    public String doPost(HttpServletRequest rq) {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        AuthUser user = securityService.login(login, password);
        if (user == null) {
            rq.setAttribute("error", "login or password invalid");
            return "login";
        }
        log.info("user {} logged", user.getLogin());
        rq.getSession().setAttribute("authUser", user);

        return "redirect:/student";
    }
}
