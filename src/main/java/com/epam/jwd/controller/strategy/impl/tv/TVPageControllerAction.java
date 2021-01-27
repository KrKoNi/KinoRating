package com.epam.jwd.controller.strategy.impl.tv;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.domain.TVSeries;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TVPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        TVSeries tvSeries = TVSeriesDAO.getInstance().findById(id);
        request.setAttribute("tv", tvSeries);

        return "tv";
    }
}
