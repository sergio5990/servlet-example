package com.github.sergio5990.servlet.example.web.servlet;

import com.github.sergio5990.servlet.example.model.AuthUser;
import com.github.sergio5990.servlet.example.service.SecurityService;
import com.github.sergio5990.servlet.example.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        rq.getSession().removeAttribute("authUser");
        rq.getSession().invalidate();
        WebUtils.forword("login", rq, rs);
    }

}
