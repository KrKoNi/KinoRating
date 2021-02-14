package com.epam.jwd.service;

import com.epam.jwd.connect.BasicConnectionPool;
import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Movie service.
 */
public class MovieService {

    private static final Logger logger = Logger.getLogger(MovieService.class);

    /**
     * Find by id movie.
     *
     * @param id the id
     * @return the movie
     */
    public static Movie findById(int id) {
        Movie movie = null;

        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            movie = MovieDAO.getInstance().findById(connection, id);
            if (movie == null) {
                return null;
            }
            movie.addGenres(ShowDAO.getInstance().getShowGenres(connection, movie));

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }

        return movie;
    }

    /**
     * Read with offset list.
     *
     * @param offset the offset
     * @param num    the num
     * @return the list
     */
    public static List<Movie> readWithOffset(int offset, int num) {

        List<Movie> movies = new ArrayList<>();

        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            movies = MovieDAO.getInstance().readWithOffset(connection, offset, num);

            for (Movie movie : movies) {
                movie.addGenres(ShowDAO.getInstance().getShowGenres(connection, movie));
            }

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }

        return movies;
    }

    /**
     * Insert.
     *
     * @param movie the movie
     */
    public static void insert(Movie movie) {

        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            ShowDAO.getInstance().insert(connection, movie);
            MovieDAO.getInstance().insert(connection, movie);
            int id = ShowDAO.getInstance().getLastInsertedId(connection);
            movie.setId(id);
            ShowDAO.getInstance().addGenresToShow(connection, movie);

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }

    }

    /**
     * Update.
     *
     * @param movie the movie
     */
    public static void update(Movie movie) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            MovieDAO.getInstance().update(connection, movie);
            ShowDAO.getInstance().update(connection, movie);
            ShowDAO.getInstance().deleteAllShowGenres(connection, movie);
            ShowDAO.getInstance().addGenresToShow(connection, movie);

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
    }

    /**
     * Gets movies count.
     *
     * @return the movies count
     */
    public static int getMoviesCount() {
        int rowCount = 0;

        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            rowCount = MovieDAO.getInstance().getRowCount(connection);

        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }

        return rowCount;
    }

    /**
     * Sort by with offset list.
     *
     * @param sortBy the sort by
     * @param order  the order
     * @param offset the offset
     * @param number the number
     * @return the list
     */
    public static List<Movie> sortByWithOffset(String sortBy, String order, int offset, int number) {
        List<Movie> movies = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            movies.addAll(MovieDAO.getInstance().getMoviesSortedBy(connection, sortBy, order, offset, number));

        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return movies;
    }

}
