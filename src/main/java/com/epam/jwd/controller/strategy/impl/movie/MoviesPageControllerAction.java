package com.epam.jwd.controller.strategy.impl.movie;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller action for movies page
 */
public class MoviesPageControllerAction implements ControllerAction {

    private final static int MOVIES_ON_PAGE = 20;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
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

        String sortBy = request.getParameter("sort");
        if (sortBy == null) {
            sortBy = "id";
        }
        String order = request.getParameter("order");
        if (order == null) {
            order = "asc";
        }

        List<Movie> movies = new ArrayList<>(MovieService.sortByWithOffset(sortBy, order, (page - 1) * MOVIES_ON_PAGE, MOVIES_ON_PAGE));

        request.setAttribute("movies", movies);
        request.setAttribute("max_page", maxPage);

        return "movies";
    }
}
