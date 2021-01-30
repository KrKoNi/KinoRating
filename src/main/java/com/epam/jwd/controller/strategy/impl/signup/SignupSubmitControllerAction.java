package com.epam.jwd.controller.strategy.impl.signup;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class SignupSubmitControllerAction implements ControllerAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String birthDate = request.getParameter("birth_date");
        String email = request.getParameter("email");

        User user = UserService.findByLoginAndPassword(login, password);

        if (user != null) {
            return "login";
        }

        else {
            user = new User(-1, login, password);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setBirthDate(LocalDate.parse(birthDate));
            user.setRole(Role.USER);
            user.setRegistrationDate(LocalDate.now());

            UserService.insert(user);

            return "index";
        }
    }


}
