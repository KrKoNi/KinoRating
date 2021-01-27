package com.epam.jwd.service;

import com.epam.jwd.dao.impl.GenreDAO;
import com.epam.jwd.domain.Genre;

import java.util.List;

public class GenreService {

    private final static List<Genre> genres = GenreDAO.getInstance().readAll();

    public static List<Genre> getGenres() {
        return genres;
    }

    public static Genre findById(int id) {
        for (Genre genre : genres) {
            if (genre.getId() == id) {
                return genre;
            }
        }

        return new Genre(-1, "Unknown");
    }

}
