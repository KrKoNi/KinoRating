package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateMoviePageControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute("genres", GenreService.getGenres());

        return "create_movie";
    }
}
