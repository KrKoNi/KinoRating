package com.epam.jwd.controller.command.impl.rate;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.domain.Show;
import com.epam.jwd.service.ShowService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SortByRateControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Show> showList = ShowService.sortByRate();

        request.setAttribute("shows", showList);

        return "sort";
    }
}
