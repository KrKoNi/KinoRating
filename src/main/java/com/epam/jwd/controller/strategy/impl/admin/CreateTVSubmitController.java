package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateTVSubmitController implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String title = request.getParameter("title");
        String shortDescription = request.getParameter("short_description");
        String description = request.getParameter("description");
        String imageLink = request.getParameter("image_link");

        TVSeries tvSeries = new TVSeries();
        tvSeries.setId(-1);
        tvSeries.setTitle(title);
        tvSeries.setShortDescription(shortDescription);
        tvSeries.setDescription(description);
        tvSeries.setImageLink(imageLink);

        TVSeriesService.insert(tvSeries);

        return "tv-series";
    }
}
