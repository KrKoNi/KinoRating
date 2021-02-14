package com.epam.jwd.filter;

import com.epam.jwd.dto.impl.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Edit user filter. Checks if user id, who is trying to make changes, is equal to edited user id.
 */
@WebFilter(filterName = "EditUserFilter", urlPatterns = "/app/update_user")
public class EditUserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        UserDTO userDTO = (UserDTO) httpServletRequest.getSession().getAttribute("userDTO");
        int editedUserId = -1;
        if (httpServletRequest.getParameter("id") != null) {
            editedUserId = Integer.parseInt(httpServletRequest.getParameter("id"));
        }

        if(editedUserId != -1 && editedUserId != userDTO.getId()) {
            httpServletResponse.sendRedirect("/app/profile");
        }

        chain.doFilter(request, response);
    }
}
