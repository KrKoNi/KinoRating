package com.epam.jwd.controller.strategy.impl.ajax;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.Show;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.ShowService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Controller action for ajax search request
 */
public class AjaxSearchControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        PrintWriter out;
        try {
            out = response.getWriter();
            String str = request.getParameter("str");
            List<Show> showList = ShowService.findLike(str);
            try {
                out.print(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(showList));
            } catch (JsonProcessingException e) {
                throw new ActionException(e);
            }
            out.flush();
        } catch (IOException e) {
            throw new ActionException(e);
        }

        return "ajax";
    }
}
