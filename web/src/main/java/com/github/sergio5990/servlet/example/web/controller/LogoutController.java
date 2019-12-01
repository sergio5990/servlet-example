package com.github.sergio5990.servlet.example.web.controller;

import com.github.sergio5990.servlet.example.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping
public class LogoutController {
    private final SecurityService securityService;

    public LogoutController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/logout")
    public String doGet(HttpSession session) {
        session.removeAttribute("authUser");
        session.invalidate();
        return "login";
    }

}
