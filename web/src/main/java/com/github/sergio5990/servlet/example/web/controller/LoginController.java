package com.github.sergio5990.servlet.example.web.controller;

import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final SecurityService securityService;

    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String doGet(HttpSession session) {
        Object authUser = session.getAttribute("authUser");
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
