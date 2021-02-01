package com.epam.jwd.controller.command.impl.tv;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TVPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        TVSeries tvSeries = TVSeriesService.findById(id);
        request.setAttribute("tv", tvSeries);

        return "tv";
    }
}
