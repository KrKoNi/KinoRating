package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateMovieSubmitControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String title = request.getParameter("title");
        String shortDescription = request.getParameter("short_description");
        String description = request.getParameter("description");
        String directedBy = request.getParameter("directed_by");
        String imageLink = request.getParameter("image_link");
        LocalDate releaseDate = LocalDate.parse(request.getParameter("release_date"));
        LocalTime duration = LocalTime.parse(request.getParameter("duration"));

        Movie movie = new Movie();
        movie.setId(-1);
        movie.setTitle(title);
        movie.setShortDescription(shortDescription);
        movie.setDescription(description);
        movie.setDuration(duration);
        movie.setReleaseDate(releaseDate);
        movie.setDirectedBy(directedBy);
        movie.setImageLink(imageLink);

        MovieService.insert(movie);

        return "movies";
    }
}
