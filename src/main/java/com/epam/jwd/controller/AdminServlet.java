package com.epam.jwd.controller;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.controller.strategy.factory.ActionFactoryMethod;
import com.epam.jwd.controller.strategy.factory.admin.AdminControllerActionFactoryMethod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin", "/admin/*"})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");

            ControllerAction action = AdminControllerActionFactoryMethod.getAction(request);
            String view = action.execute(request, response);

            request.getRequestDispatcher("/WEB-INF/jsp/admin/" + view + ".jsp").forward(request, response);

        }
        catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }
}
