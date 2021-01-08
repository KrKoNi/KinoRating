package com.epam.jwd.strategy.impl;

import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.User;
import com.epam.jwd.strategy.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = UserDAO.getInstance().findByLoginAndPassword(login, password);
        if (user != null) {
            request.getSession().setAttribute("user", UserConverter.getInstance().toDto(user));
            return "index";
        }
        else {
            request.setAttribute("error", "Unknown username/password. Please retry.");
            return "login";
        }
    }


}
