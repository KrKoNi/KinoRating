package com.epam.jwd.controller;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.controller.strategy.factory.ajax.AjaxControllerActionFactoryMethod;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.service.MovieService;
import com.epam.jwd.service.ShowService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

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
