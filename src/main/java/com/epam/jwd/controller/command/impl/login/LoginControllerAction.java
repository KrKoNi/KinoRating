package com.epam.jwd.controller.command.impl.login;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LoginControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Map<String, String> messages = new HashMap<>();

        if (login == null || login.isEmpty()) {
            messages.put("login", "Please enter login");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }

        if (messages.isEmpty()) {
            User user = UserService.findByLoginAndPassword(login, password);

            if (user != null) {
                request.getSession().setAttribute("userDTO", UserConverter.getInstance().toDto(user));
                return "index";
            } else {
                messages.put("login", "Unknown login, please try again");
            }
        }

        request.setAttribute("messages", messages);
        return "login";
    }


}
