package com.epam.jwd.controller;

import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.TVSeries;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AjaxServlet", urlPatterns = "/ajax")
public class AjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        switch (command) {
            case "movies":
                List<Movie> movieList = MovieDAO.getInstance().readWithOffset(0, 20);
                out.print(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(movieList));
                out.flush();
                break;
            case "tv":
                List<TVSeries> tvSeries = TVSeriesDAO.getInstance().readWithOffset(0,20);
                out.print(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(tvSeries));
                out.flush();
                break;
            case "search":
                String str = request.getParameter("str");
                List<Movie> movies = MovieDAO.getInstance().findLike(str);
                out.print(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(movies));
                out.flush();
                break;
            default:
                break;
        }
    }
}
