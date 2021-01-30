package com.epam.jwd.controller.strategy.impl.user;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.domain.User;
import com.epam.jwd.dto.impl.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("userDTO");

        User user = UserConverter.getInstance().toObject(userDTO);

        request.setAttribute("user", user);

        return "user";
    }
}
