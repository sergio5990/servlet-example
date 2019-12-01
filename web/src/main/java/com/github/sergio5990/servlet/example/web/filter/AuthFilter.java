package com.github.sergio5990.servlet.example.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/student")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) servletRequest;
        Object authUser = rq.getSession().getAttribute("authUser");
        if (authUser == null) {
            HttpServletResponse rs = (HttpServletResponse) servletResponse;
            rs.sendRedirect(rq.getContextPath() + "/login");
            return;
        }
        filterChain.doFilter(rq, servletResponse);
    }
}
