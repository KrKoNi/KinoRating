package com.epam.jwd.strategy.impl;

import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.strategy.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieEditAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int movie_id = Integer.parseInt(request.getParameter("movie_id"));
        Movie movie = MovieDAO.getInstance().findById(movie_id);
        request.setAttribute("movie", movie);

        return "movie_edit";
    }
}
