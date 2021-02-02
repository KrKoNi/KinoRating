package com.epam.jwd.controller.command.impl.tv;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.service.MovieService;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TVSeriesPageControllerAction implements ControllerAction {

    private final static int TV_ON_PAGE = 20;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int tvCount = TVSeriesService.getTVCount();

        int maxPage = (int) Math.ceil((double) tvCount / TV_ON_PAGE);

        int page;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }

        if (page > maxPage) {
            page = maxPage;
        }

        List<TVSeries> tvSeries = TVSeriesService.readWithOffset((page-1) * TV_ON_PAGE, TV_ON_PAGE);

        request.setAttribute("tv_series", tvSeries);
        request.setAttribute("max_page", maxPage);

        return "tv-series";
    }
}
