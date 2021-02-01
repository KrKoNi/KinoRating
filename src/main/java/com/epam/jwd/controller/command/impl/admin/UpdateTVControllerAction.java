package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateTVControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int tvSeriesId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String shortDescription = request.getParameter("short_description");
        String description = request.getParameter("description");
        String imageLink = request.getParameter("image_link");

        TVSeries tvSeries = new TVSeries();
        tvSeries.setId(tvSeriesId);
        tvSeries.setTitle(title);
        tvSeries.setShortDescription(shortDescription);
        tvSeries.setDescription(description);
        tvSeries.setImageLink(imageLink);

        TVSeriesService.update(tvSeries);

        return "tv-series";
    }
}
