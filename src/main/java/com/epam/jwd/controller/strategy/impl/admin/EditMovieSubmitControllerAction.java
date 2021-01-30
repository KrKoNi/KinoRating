package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.domain.Movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

public class EditMovieSubmitControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int movieId = Integer.parseInt(request.getParameter("movie_id"));
        String title = request.getParameter("title");
        String shortDescription = request.getParameter("short_description");
        String description = request.getParameter("description");

        Movie movie = new Movie(movieId, title);
        movie.setShortDescription(shortDescription);
        movie.setDescription(description);
        movie.setDuration(LocalTime.MIN);
        movie.setReleaseDate(LocalDate.now());
        movie.setDirectedBy("Somebody");
        movie.setImageLink("");

        MovieDAO.getInstance().update(movie);

        return "movies";
    }
}