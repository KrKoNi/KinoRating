package com.epam.jwd.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Rate auth filter, checks if user, who is trying to rate show, is logged in.
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = "/ajax/rate")
public class RateAuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        String loginURI = httpServletRequest.getContextPath() + "/app/login";

        boolean loggedIn = session != null && session.getAttribute("userDTO") != null;
        boolean loginRequest = httpServletRequest.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            PrintWriter out = response.getWriter();
            out.print("You have to be authorized to be able to rate");
            out.flush();
        }
    }
}
