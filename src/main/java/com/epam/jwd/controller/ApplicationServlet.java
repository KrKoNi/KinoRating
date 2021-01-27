package com.epam.jwd.controller;

import com.epam.jwd.controller.strategy.factory.ActionFactoryMethod;
import com.epam.jwd.controller.strategy.ControllerAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ApplicationServlet", urlPatterns = {"/app/*", "/app"})
public class ApplicationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            ControllerAction action = ActionFactoryMethod.getAction(request);
            String view = action.execute(request, response);

            request.getRequestDispatcher("/WEB-INF/jsp/" + view + ".jsp").forward(request, response);

        }
        catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }
}
