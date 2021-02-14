package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.ShowService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller action for remove show
 */
public class RemoveShowControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int showId = Integer.parseInt(request.getParameter("id"));

        ShowService.removeShow(showId);

        return "movies"; //todo
    }
}
