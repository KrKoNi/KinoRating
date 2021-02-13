package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminMoviesPageControllerAction implements ControllerAction {

    private final static int MOVIES_ON_PAGE = 20;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int movieCount = MovieService.getMoviesCount();

        int maxPage = (int) Math.ceil((double) movieCount / MOVIES_ON_PAGE);

        int page;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }

        if (page > maxPage) {
            page = maxPage;
        }

        List<Movie> movies = MovieService.readWithOffset((page-1) * MOVIES_ON_PAGE, MOVIES_ON_PAGE);

        request.setAttribute("movies", movies);
        request.setAttribute("max_page", maxPage);

        return "movies";
    }
}
