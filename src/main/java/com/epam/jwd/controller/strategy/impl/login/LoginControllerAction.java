package com.epam.jwd.controller.strategy.impl.login;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.User;

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
            User user = UserDAO.getInstance().findByLoginAndPassword(login, password);

            if (user != null) {
                request.getSession().setAttribute("user", UserConverter.getInstance().toDto(user));
                return "index";
            } else {
                messages.put("login", "Unknown login, please try again");
            }
        }

        request.setAttribute("messages", messages);
        return "login";
    }


}
