package com.epam.jwd.strategy.impl;

import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.strategy.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MoviesPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        List<Movie> movies = MovieDAO.getInstance().readSome(10);

        request.setAttribute("movies", movies);
        return "movies";
    }
}
