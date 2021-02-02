package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.domain.User;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.service.MovieService;
import com.epam.jwd.service.ShowService;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class UserPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("userDTO");

        User user = UserConverter.getInstance().toObject(userDTO);

        Set<Integer> showIds = user.getRates().keySet();

        List<Show> shows = new ArrayList<>();

        for (Integer showId : showIds) {
            Movie movie;
            try {
                movie = MovieService.findById(showId);
            } catch (NullPointerException exception) {
                movie = null;
            }
            TVSeries tvSeries;
            try {
                tvSeries = TVSeriesService.findById(showId);
            } catch (SQLException exception) {
                tvSeries = null;
            }

            if (movie != null) {
                shows.add(movie);
            } else if (tvSeries != null) {
                shows.add(tvSeries);
            }
        }

        request.setAttribute("user", user);
        request.setAttribute("shows", shows);

        return "user";
    }
}
