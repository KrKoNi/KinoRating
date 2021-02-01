package com.epam.jwd.controller.strategy.impl.signup;

import com.epam.jwd.config.MailProperties;
import com.epam.jwd.config.MailThread;
import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.UserService;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class SignupSubmitControllerAction implements ControllerAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        //String firstName = request.getParameter("first_name");
        //String lastName = request.getParameter("last_name");
        //String birthDate = request.getParameter("birth_date");
        String email = request.getParameter("email");

        User user = UserService.findByLoginAndPassword(login, password);

        if (user != null) {
            return "login";
        }
        user = new User(-1, login, password);
        user.setEmail(email);
        user.setFirstName("");
        user.setLastName("");
        user.setBirthDate(LocalDate.EPOCH);
        user.setRole(Role.USER);
        user.setRegistrationDate(LocalDate.now());

        UserService.insert(user);

        MailThread mailOperator = new MailThread(email, "Sign up", "Thank you for registration on KinoRating, " + login + "! We hope you'll enjoy our application!");
        mailOperator.start();

        return "index";
    }


}
