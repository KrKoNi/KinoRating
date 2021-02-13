package com.epam.jwd.controller.strategy.impl.search;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.Show;
import com.epam.jwd.service.ShowService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchShowsControllerAction implements ControllerAction {
    private static final int SHOWS_ON_PAGE = 20;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


        String str = request.getParameter("search");

        List<Show> showList = ShowService.findLike(str);

        request.setAttribute("shows", showList);

        return "search_result";
    }
}
