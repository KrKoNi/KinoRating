package com.epam.jwd.controller.strategy.impl.movie;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.dto.impl.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MoviesPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }
        List<Movie> movies = MovieDAO.getInstance().readWithOffset((page-1) * 20, 20);

        request.setAttribute("movies", movies);
        return "movies";
    }
}