package com.epam.jwd.filter;

import com.epam.jwd.domain.Role;
import com.epam.jwd.dto.impl.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Admin filter for all admin requests, allows only for users who have admin role.
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin", "/admin/*"})
public class AdminFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        UserDTO userDTO = (UserDTO) httpServletRequest.getSession(false).getAttribute("userDTO");

        if (userDTO == null || userDTO.getRole() != Role.ADMIN) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/app/index");
        } else {
            chain.doFilter(request, response);
        }

    }
}
