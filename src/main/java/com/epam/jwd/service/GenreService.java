package com.epam.jwd.service;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.impl.GenreDAO;
import com.epam.jwd.domain.Genre;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreService {

    private static final List<Genre> genres = initGenres();

    public static List<Genre> getGenres() {
        return genres;
    }

    private static List<Genre> initGenres() {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<Genre> genreList = new ArrayList<>();
        try {
             genreList = GenreDAO.getInstance().readAll(connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return genreList;
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
