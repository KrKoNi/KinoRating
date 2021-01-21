package com.epam.jwd.strategy.impl;

import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;
import com.epam.jwd.strategy.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class SignupPostAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String birthDate = request.getParameter("birth_date");
        String email = request.getParameter("email");
        LocalDate registrationDate = LocalDate.now();

        User user = UserDAO.getInstance().findByLoginAndPassword(login, password);

        if (user != null) {
            request.getSession().setAttribute("error", "User already exists");

            return "signup";
        }

        else {
            user = new User(0, login);
            user.setEmail(email);
            user.setRole(Role.USER);
            user.setBirthDate(LocalDate.parse(birthDate));
            user.setRegistrationDate(registrationDate);
            user.setPassword(password);
            user.setLastName(lastName);
            user.setFirstName(firstName);

            UserDAO.getInstance().insert(user);

            request.setAttribute("message", "User was successfully created");

            return "index";
        }
    }


}
