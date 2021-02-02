package com.epam.jwd.service;

import com.epam.jwd.connect.BasicConnectionPool;
import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.exceptions.DaoException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieService {

    public static Movie findById(int id) {
        Movie movie = null;

        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            movie = MovieDAO.getInstance().findById(connection, id);
            if(movie == null) {
                return null;
            }
            movie.addGenres(ShowDAO.getInstance().getShowGenres(connection, movie));
            movie.addRates(ShowDAO.getInstance().getShowRates(connection, movie));

            connection.commit();
        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }

        return movie;
    }

    public static List<Movie> readWithOffset(int offset, int num) {

        List<Movie> movies = new ArrayList<>();

        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            movies = MovieDAO.getInstance().readWithOffset(connection, offset, num);

            for (Movie movie : movies) {
                movie.addGenres(ShowDAO.getInstance().getShowGenres(connection, movie));
                movie.addRates(ShowDAO.getInstance().getShowRates(connection, movie));
            }

            connection.commit();
        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }

        return movies;
    }

    public static void insert(Movie movie) {

        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            ShowDAO.getInstance().insert(connection, movie);
            MovieDAO.getInstance().insert(connection, movie);
            ShowDAO.getInstance().addGenresToShow(connection, movie);

            connection.commit();
        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }
    }

    public static void update(Movie movie) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            MovieDAO.getInstance().update(connection, movie);
            ShowDAO.getInstance().update(connection, movie);
            ShowDAO.getInstance().deleteAllShowGenres(connection, movie);
            ShowDAO.getInstance().addGenresToShow(connection, movie);

            connection.commit();
        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }
    }

    public static int getMoviesCount() {
        int rowCount = 0;

        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            rowCount = MovieDAO.getInstance().getRowCount(connection);

            connection.commit();

        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }

        return rowCount;
    }


}
