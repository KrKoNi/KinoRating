package com.epam.jwd.service;

import com.epam.jwd.connect.BasicConnectionPool;
import com.epam.jwd.connect.ProxyConnection;
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
        List<Genre> genreList = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            genreList = GenreDAO.getInstance().readAll(connection);
            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace(); //logs
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
