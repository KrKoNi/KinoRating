package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller action for remove user
 */
public class RemoveUserControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int userId = Integer.parseInt(request.getParameter("id"));

        UserService.removeUser(userId);

        return "users";
    }
}
