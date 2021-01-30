package com.epam.jwd.controller.strategy.impl.movie;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoviePageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Movie movie = (Movie) request.getAttribute("movie");
        if (movie == null) {
            int movieId = Integer.parseInt(request.getParameter("id"));
            movie = MovieService.findById(movieId);
            request.setAttribute("movie", movie);
        }

        return "movie";
    }
}
