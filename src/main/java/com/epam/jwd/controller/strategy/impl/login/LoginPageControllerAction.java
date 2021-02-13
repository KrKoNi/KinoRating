package com.epam.jwd.controller.strategy.impl.login;

import com.epam.jwd.controller.strategy.ControllerAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "login";
    }
}
