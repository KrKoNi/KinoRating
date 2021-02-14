package com.epam.jwd.controller.strategy.impl.user;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.domain.User;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.MovieService;
import com.epam.jwd.service.ShowService;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Controller action for profile page
 */
public class UserPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, SQLException {

        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("userDTO");

        User user = UserConverter.getInstance().toObject(userDTO);

        Set<Integer> showIds = user.getRates().keySet();

        List<Show> shows = new ArrayList<>();

        for (Integer showId : showIds) {
            Show show;
            Class<? extends Show> showClass = null;
            try {
                showClass = ShowService.getShowType(showId);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (showClass == Movie.class) {
                show = MovieService.findById(showId);
            } else if (showClass == TVSeries.class) {
                show = TVSeriesService.findById(showId);
            } else show = null;

            shows.add(show);

        }

        request.setAttribute("user", user);
        request.setAttribute("shows", shows);

        return "user";
    }
}
