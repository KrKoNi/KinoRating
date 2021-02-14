package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ChangeUserRoleControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, SQLException {

        int userId = Integer.parseInt(request.getParameter("id"));

        UserService.changeRole(userId);

        return "users";
    }
}
