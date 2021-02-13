package com.epam.jwd.controller.strategy.impl.tv;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class TVPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int id = Integer.parseInt(request.getParameter("id"));
        TVSeries tvSeries = null;
        try {
            tvSeries = TVSeriesService.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("tv", tvSeries);

        return "tv";
    }
}
