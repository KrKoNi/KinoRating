package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.Genre;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.GenreService;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller action for create tv submit request
 */
public class CreateTVSubmitController implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String title = request.getParameter("title");
        String shortDescription = request.getParameter("short_description");
        String description = request.getParameter("description");
        String imageLink = request.getParameter("image_link");
        List<Genre> genres = Arrays.stream(request.getParameterValues("genres"))
                .map(s -> GenreService.findById(Integer.parseInt(s)))
                .collect(Collectors.toList());

        TVSeries tvSeries = new TVSeries();
        tvSeries.setId(-1);
        tvSeries.setTitle(title);
        tvSeries.setShortDescription(shortDescription);
        tvSeries.setDescription(description);
        tvSeries.setImageLink(imageLink);
        tvSeries.addGenres(genres);

        TVSeriesService.insert(tvSeries);

        return "tv-series";
    }
}
