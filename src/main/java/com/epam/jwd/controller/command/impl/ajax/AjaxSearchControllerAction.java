package com.epam.jwd.controller.command.impl.ajax;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.domain.Show;
import com.epam.jwd.service.ShowService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

public class AjaxSearchControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PrintWriter out = response.getWriter();
        String str = request.getParameter("str");
        List<Show> showList = ShowService.findLike(str);
        out.print(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(showList));
        out.flush();

        return "ajax";
    }
}
