package com.epam.jwd.controller.command.impl.movie;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MoviesPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("userDTO");

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
