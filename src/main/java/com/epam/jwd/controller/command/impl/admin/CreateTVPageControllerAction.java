package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.command.ControllerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateTVPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "create_tv";
    }
}
