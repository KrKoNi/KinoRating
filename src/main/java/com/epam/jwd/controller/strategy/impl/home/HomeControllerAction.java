package com.epam.jwd.controller.strategy.impl.home;

import com.epam.jwd.controller.strategy.ControllerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "index";
    }
}
