package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateMovieControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int movieId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String shortDescription = request.getParameter("short_description");
        String description = request.getParameter("description");
        String directedBy = request.getParameter("directed_by");
        String imageLink = request.getParameter("image_link");
        LocalDate releaseDate = LocalDate.parse(request.getParameter("release_date"));
        LocalTime duration = LocalTime.parse(request.getParameter("duration"));

        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setTitle(title);
        movie.setShortDescription(shortDescription);
        movie.setDescription(description);
        movie.setDuration(duration);
        movie.setReleaseDate(releaseDate);
        movie.setDirectedBy(directedBy);
        movie.setImageLink(imageLink);

        MovieService.update(movie);

        return "movies";
    }
}
