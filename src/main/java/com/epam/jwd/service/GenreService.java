package com.epam.jwd.service;

import com.epam.jwd.connect.BasicConnectionPool;
import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.impl.GenreDAO;
import com.epam.jwd.domain.Genre;
import com.epam.jwd.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Genre service.
 */
public class GenreService {

    private static final Logger logger = Logger.getLogger(GenreService.class);

    private static final List<Genre> genres = initGenres();

    /**
     * Gets genres.
     *
     * @return the genres
     */
    public static List<Genre> getGenres() {
        return genres;
    }

    private static List<Genre> initGenres() {
        List<Genre> genreList = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            genreList = GenreDAO.getInstance().readAll(connection);
            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return genreList;
    }

    /**
     * Find genre by id.
     *
     * @param id the id
     * @return the genre
     */
    public static Genre findById(int id) {
        for (Genre genre : genres) {
            if (genre.getId() == id) {
                return genre;
            }
        }

        return new Genre(-1, "Unknown");
    }

}
