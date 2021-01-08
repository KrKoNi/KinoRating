package com.epam.jwd.controller;

import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.User;
import com.epam.jwd.strategy.Action;
import com.epam.jwd.strategy.factory.ActionFactoryMethod;
import com.epam.jwd.strategy.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ApplicationServlet", urlPatterns = "/app")
public class ApplicationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("command");
            String view;
            if (action == null) {
                action = "index";
            }

            switch (action) {
                case "login":
                    view = "login";
                    break;
                case "login-post":
                    view = new LoginAction().execute(request, response);
                    break;
                case "movies":
                    view = new MoviesPageAction().execute(request, response);
                    break;
                case "logout":
                    view = new LogoutAction().execute(request, response);
                    break;
                case "signup":
                    view = "signup";
                    break;
                case "signup-post":
                    view = new SignupPostAction().execute(request, response);
                    break;
                case "movie_edit":
                    view = new MovieEditAction().execute(request, response);
                    break;
                default:
                    view = "index";
            }

            request.getRequestDispatcher("/WEB-INF/jsp/" + view + ".jsp").forward(request, response);

        }
        catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }
}
