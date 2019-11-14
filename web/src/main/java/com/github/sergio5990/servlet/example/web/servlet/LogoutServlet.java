package com.github.sergio5990.servlet.example.web.servlet;

import com.github.sergio5990.servlet.example.service.SecurityService;
import com.github.sergio5990.servlet.example.web.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping
public class LogoutServlet {
    @Autowired
    private SecurityService securityService;

    @GetMapping("/logout")
    public String doGet(HttpServletRequest rq) {
        rq.getSession().removeAttribute("authUser");
        rq.getSession().invalidate();
        return "login";
    }

}
