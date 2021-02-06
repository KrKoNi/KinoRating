package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.dto.impl.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("userDTO");
        request.setAttribute("user", UserConverter.getInstance().toObject(userDTO));

        return "edit_user";
    }
}
