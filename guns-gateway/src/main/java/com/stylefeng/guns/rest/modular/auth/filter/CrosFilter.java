package com.stylefeng.guns.rest.modular.auth.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenzhibin
 * @time 2020/5/8 16:17
 */
@Component
public class CrosFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response1 = (HttpServletResponse) servletResponse;
        HttpServletRequest request1 = (HttpServletRequest) servletRequest;
        response1.setHeader("Access-Control-Allow-Origin", "*");
        response1.setHeader("Access-Control-Allow-Credentials", "true");
        response1.setHeader("Access-Control-Allow-Methods", "*");
        response1.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Authorization");
        response1.setHeader("Access-Control-Expose-Headers", "*");
        if (request1.getMethod().equals(RequestMethod.OPTIONS.toString())) {
            System.out.println("-----检查------");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
