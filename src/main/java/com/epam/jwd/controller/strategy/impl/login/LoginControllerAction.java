package com.epam.jwd.controller.strategy.impl.login;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.domain.User;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.UserService;
import com.epam.jwd.validator.Validator;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.isEmpty() || password.isEmpty()) {
            request.setAttribute("Error", "Login or password is empty");
            return "login";
        }

        User user;

        if (Validator.checkEmail(login)) {
            user = UserService.findByEmail(login);
            if (user == null) {
                user = UserService.findByLogin(login);
            }
        } else {
            user = UserService.findByLogin(login);
        }

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            request.getSession(true).setAttribute("userDTO", UserConverter.getInstance().toDto(user));
            return "index";
        } else {
            request.setAttribute("error", "User not found");
            return "login";
        }
    }


}
