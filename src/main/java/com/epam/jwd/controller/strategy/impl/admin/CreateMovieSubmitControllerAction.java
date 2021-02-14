package com.epam.jwd.controller.strategy.impl.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.domain.Genre;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.exceptions.ActionException;
import com.epam.jwd.service.GenreService;
import com.epam.jwd.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller action for create movie submit request.
 */
public class CreateMovieSubmitControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String title = request.getParameter("title");
        String shortDescription = request.getParameter("short_description");
        String description = request.getParameter("description");
        String directedBy = request.getParameter("directed_by");
        String imageLink = request.getParameter("image_link");
        LocalDate releaseDate = LocalDate.parse(request.getParameter("release_date"));
        LocalTime duration = LocalTime.parse(request.getParameter("duration"));
        List<Genre> genres = Arrays.stream(request.getParameterValues("genres"))
                .map(s -> GenreService.findById(Integer.parseInt(s)))
                .collect(Collectors.toList());

        Movie movie = new Movie();
        movie.setId(-1);
        movie.setTitle(title);
        movie.setShortDescription(shortDescription);
        movie.setDescription(description);
        movie.setDuration(duration);
        movie.setReleaseDate(releaseDate);
        movie.setDirectedBy(directedBy);
        movie.setImageLink(imageLink);
        movie.addGenres(genres);

        MovieService.insert(movie);

        return "movies";
    }
}
