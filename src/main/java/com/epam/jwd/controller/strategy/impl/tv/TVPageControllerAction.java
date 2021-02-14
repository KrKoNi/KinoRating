package com.epam.jwd.controller.strategy.impl.tv;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controller action for a tv page
 */
public class TVPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int id = Integer.parseInt(request.getParameter("id"));
        TVSeries tvSeries = null;
        try {
            tvSeries = TVSeriesService.findById(id);
        } catch (SQLException throwables) {
            throw new ActionException("TV not found", throwables);
        }
        UserDTO userDTO = (UserDTO) request.getSession(false).getAttribute("userDTO");

        if(userDTO != null) {
            Map<Integer, Byte> rates = UserConverter.getInstance().toObject(userDTO).getRates();

            if (tvSeries != null) {
                tvSeries.setCurrentUserRate(rates.containsKey(id) ? rates.get(id) : 0);
            }
        }
        request.setAttribute("tv", tvSeries);

        return "tv";
    }
}
