package com.epam.jwd.controller.command.impl.login;

import com.epam.jwd.controller.command.ControllerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        return "index";
    }
}
