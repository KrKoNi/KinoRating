package com.epam.jwd.controller.command.impl.signup;

import com.epam.jwd.config.MailThread;
import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.UserService;
import com.epam.jwd.validator.Validator;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;

public class SignupSubmitControllerAction implements ControllerAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if( !(Validator.checkEmail(email) && Validator.checkLogin(login) && Validator.checkPassword(password))  ) {
            request.setAttribute("error", "Validation error");
        }

        User user = UserService.findByLogin(login);

        if (user == null) {
            user = UserService.findByEmail(email);
        }

        if (user != null) {
            request.setAttribute("error", "User already exists");
            return "login";
        }

        user = new User(-1, login, BCrypt.hashpw(password, BCrypt.gensalt()));

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
