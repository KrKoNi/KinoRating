package com.epam.jwd.strategy.impl;

import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.User;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.strategy.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieRateSubmitAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        int movieId = Integer.parseInt(request.getParameter("movie_id"));
        Movie movie = MovieDAO.getInstance().findById(movieId);
        byte rate = Byte.parseByte(request.getParameter("rate"));

        User user = UserDAO.getInstance().findById(userDTO.getId());
        user.getRates().put(movieId, rate);
        movie.getRates().put(user.getId(), rate);

        ShowDAO.getInstance().addRate(movie, user, rate);

        request.setAttribute("movie", movie);
        return "movie";
    }
}
