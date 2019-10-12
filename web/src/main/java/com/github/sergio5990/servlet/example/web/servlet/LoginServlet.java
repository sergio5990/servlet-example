package com.github.sergio5990.servlet.example.web.servlet;

import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.service.SecurityService;
import com.github.sergio5990.servlet.example.service.impl.DefaultSecurityService;
import com.github.sergio5990.servlet.example.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    private SecurityService securityService = DefaultSecurityService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        Object authUser = rq.getSession().getAttribute("authUser");
        if (authUser == null) {
            WebUtils.forward("login", rq, rs);
            return;
        }
        WebUtils.redirect("/student", rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        AuthUser user = securityService.login(login, password);
        if (user == null) {
            rq.setAttribute("error", "login or password invalid");
            WebUtils.forward("login", rq, rs);
            return;
        }
        log.info("user {} logged", user.getLogin());
        rq.getSession().setAttribute("authUser", user);
        WebUtils.redirect("/student", rq, rs);
    }
}
