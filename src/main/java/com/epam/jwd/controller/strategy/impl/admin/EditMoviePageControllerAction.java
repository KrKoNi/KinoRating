package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditMoviePageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int movieId = Integer.parseInt(request.getParameter("movie_id"));
        Movie movie = MovieService.findById(movieId);
        request.setAttribute("movie", movie);

        return "movie_edit";
    }
}
