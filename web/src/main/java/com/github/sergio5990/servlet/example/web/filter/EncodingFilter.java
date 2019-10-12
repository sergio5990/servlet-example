package com.github.sergio5990.servlet.example.web.filter;

import com.github.sergio5990.servlet.example.web.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {

    private static final String UTF_8 = "UTF-8";

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain filterChain) throws IOException, ServletException {
        rq.setCharacterEncoding(UTF_8);
        filterChain.doFilter(rq, rs);
        rs.setCharacterEncoding(UTF_8);
    }
}
