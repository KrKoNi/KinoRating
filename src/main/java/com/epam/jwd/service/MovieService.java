package com.epam.jwd.service;

import com.epam.jwd.dao.impl.GenreDAO;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.domain.Movie;

import java.sql.SQLException;
import java.util.List;

public class MovieService {

    private static final MovieService INSTANCE = new MovieService();

    private MovieService() {
    }

    public static MovieService getInstance() {
        return INSTANCE;
    }

    public List<Movie> readWithOffset(int offset, int num) {
        List<Movie> movies = MovieDAO.getInstance().readWithOffset(offset, num);

        for (Movie movie : movies) {
            movie.addGenres(GenreDAO.getInstance().getGenresByShow(movie));
        }

        return movies;
    }

    public void insert(Movie movie) {
        try {
            MovieDAO.getInstance().insert(movie);
            ShowDAO.getInstance().insert(movie);
            GenreDAO.getInstance().addGenresToShow(movie);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


}
