package com.epam.jwd.controller.strategy.impl.tv;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.domain.TVSeries;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TVSeriesPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }
        List<TVSeries> tvSeries = TVSeriesDAO.getInstance().readWithOffset((page-1) * 20, 20);

        request.setAttribute("tvseries", tvSeries);

        return "tv-series";
    }
}