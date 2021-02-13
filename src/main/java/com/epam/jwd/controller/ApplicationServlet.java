package com.epam.jwd.controller;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.controller.strategy.factory.ActionFactoryMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ApplicationServlet", urlPatterns = {"/app/*", "/app"})
public class ApplicationServlet extends HttpServlet {
    String view;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/app/" + view);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/" + view + ".jsp").forward(request, response);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ControllerAction action = ActionFactoryMethod.getAction(request);
            view = action.execute(request, response);

            super.service(request, response);

        }
        catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }
}
