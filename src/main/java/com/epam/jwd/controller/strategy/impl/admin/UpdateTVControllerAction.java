package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller action for update tv info
 */
public class UpdateTVControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

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
