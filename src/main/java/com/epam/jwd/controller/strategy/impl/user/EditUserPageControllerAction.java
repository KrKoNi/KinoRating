package com.epam.jwd.controller.strategy.impl.user;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.exceptions.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("userDTO");
        request.setAttribute("user", UserConverter.getInstance().toObject(userDTO));

        return "edit_user";
    }
}
