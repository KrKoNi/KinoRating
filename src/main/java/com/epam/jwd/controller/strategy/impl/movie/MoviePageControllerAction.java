package com.epam.jwd.controller.strategy.impl.movie;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Controller action for movie page
 */
public class MoviePageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int movieId = Integer.parseInt(request.getParameter("id"));
        Movie movie = MovieService.findById(movieId);
        UserDTO userDTO = (UserDTO) request.getSession(false).getAttribute("userDTO");

        if (userDTO != null) {
            Map<Integer, Byte> rates = UserConverter.getInstance().toObject(userDTO).getRates();

            if (movie != null) {
                movie.setCurrentUserRate(rates.containsKey(movieId) ? rates.get(movieId) : 0);
            }
        }

        request.setAttribute("movie", movie);

        return "movie";
    }
}
