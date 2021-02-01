package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminMoviesPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }

        List<Movie> movies = MovieService.readWithOffset((page-1) * 20, 20);

        request.setAttribute("movies", movies);
        return "movies";
    }
}
