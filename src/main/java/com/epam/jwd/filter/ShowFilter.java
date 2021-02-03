package com.epam.jwd.filter;

import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.service.ShowService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ShowFilter", urlPatterns = "/app/show")
public class ShowFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        int id = Integer.parseInt(httpServletRequest.getParameter("id"));

        Class<? extends Show> showClass = null;
        try {
            showClass = ShowService.getShowType(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (showClass == Movie.class) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/app/movie?id=" + id);
        } else if (showClass == TVSeries.class) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/app/tv?id=" + id);
        }
    }
}
