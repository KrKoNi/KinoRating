package com.epam.jwd.controller.strategy.impl.tv;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.TVSeriesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller action for tvs page
 */
public class TVSeriesPageControllerAction implements ControllerAction {

    private final static int TV_ON_PAGE = 20;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

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

        String sortBy = request.getParameter("sort");
        if (sortBy == null) {
            sortBy = "id";
        }
        String order = request.getParameter("order");
        if (order == null) {
            order = "asc";
        }

        List<TVSeries> tvSeries = TVSeriesService.sortByWithOffset(sortBy, order, (page-1) * TV_ON_PAGE, TV_ON_PAGE);

        request.setAttribute("tv_series", tvSeries);
        request.setAttribute("max_page", maxPage);

        return "tv-series";
    }
}
