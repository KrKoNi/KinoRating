package com.epam.jwd.controller.strategy.impl.signup;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;

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

        User user = UserDAO.getInstance().findByLoginAndPassword(login, password);

        if (user != null) {
            return "signup";
        }

        else {
            user = User.builder()
                    .setId(0).setLogin(login)
                    .setPassword(password).setEmail(email)
                    .setFirstName(firstName).setLastName(lastName)
                    .setBirthDate(LocalDate.parse(birthDate)).setRole(Role.USER)
                    .build();

            UserDAO.getInstance().insert(user);

            return "index";
        }
    }


}
