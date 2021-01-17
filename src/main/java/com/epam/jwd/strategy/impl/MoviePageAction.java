package com.epam.jwd.strategy.impl;

import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.strategy.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoviePageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Movie movie = (Movie) request.getAttribute("movie");
        if (movie == null) {
            int movieId = Integer.parseInt(request.getParameter("movie_id"));
            movie = MovieDAO.getInstance().findById(movieId);
            request.setAttribute("movie", movie);
        }

        return "movie";
    }
}
