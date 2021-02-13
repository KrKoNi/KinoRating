package com.epam.jwd.controller.strategy.impl.user;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class EditUserSubmitControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));

        User user = UserService.findById(id);

        String login = request.getParameter("login");

        User duplicateLoginUser = UserService.findByLogin(login);

        if (duplicateLoginUser != null && !user.getLogin().equals(login)) {
            return "user";
        }

        String email = request.getParameter("email");

        User duplicateEmailUser = UserService.findByEmail(email);

        if (duplicateEmailUser != null && !user.getEmail().equals(email)) {
            return "user";
        }

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birth_date"));

        user.setLogin(login);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);

        UserService.update(user);

        return "user";
    }
}
