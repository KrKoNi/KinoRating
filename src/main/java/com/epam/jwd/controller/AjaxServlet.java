package com.epam.jwd.controller;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.controller.strategy.factory.ajax.AjaxControllerActionFactoryMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for ajax requests
 */
@WebServlet(name = "AjaxServlet", urlPatterns = "/ajax/*")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ControllerAction action = AjaxControllerActionFactoryMethod.getAction(request);
            action.execute(request, response);

            super.service(request, response);

        }
        catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }
}
