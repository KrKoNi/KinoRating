package com.epam.jwd.controller.strategy.impl.rate;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.User;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.service.MovieService;
import com.epam.jwd.service.ShowService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieRateSubmitControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("userDTO");
        int movieId = Integer.parseInt(request.getParameter("movie_id"));
        Movie movie = MovieService.findById(movieId);
        byte rate = Byte.parseByte(request.getParameter("rate"));

        movie.getRates().put(userDTO.getId(), rate);

        ShowService.addRate(movie, userDTO.getId(), rate);

        request.setAttribute("movie", movie);
        return "movie";
    }
}
