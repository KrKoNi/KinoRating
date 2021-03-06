package com.epam.jwd.controller.strategy.impl.rate;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.MovieService;
import com.epam.jwd.service.ShowService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller action for rate request
 */
public class MovieRateSubmitControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("userDTO");
        int movieId = Integer.parseInt(request.getParameter("movie_id"));
        Movie movie = MovieService.findById(movieId);
        byte rate = Byte.parseByte(request.getParameter("rate"));

        ShowService.addRate(movie, userDTO.getId(), rate);

        request.setAttribute("movie", movie);
        return "movie";
    }
}
