package com.epam.jwd.service;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.impl.GenreDAO;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.domain.Movie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieService {

    public static Movie findById(int id) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        Movie movie = null;
        try {
            connection.setAutoCommit(false);

            movie = MovieDAO.getInstance().findById(connection, id);
            movie.addGenres(ShowDAO.getInstance().getShowGenres(connection, movie));
            movie.addRates(ShowDAO.getInstance().getShowRates(connection, movie));

            connection.commit();
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            exception.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }

        return movie;
    }

    public static List<Movie> readWithOffset(int offset, int num) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<Movie> movies = new ArrayList<>();
        try {
            connection.setAutoCommit(false);

            movies = MovieDAO.getInstance().readWithOffset(connection, offset, num);
            for (Movie movie : movies) {
                movie.addGenres(ShowDAO.getInstance().getShowGenres(connection, movie));
                movie.addRates(ShowDAO.getInstance().getShowRates(connection, movie));
            }

            connection.commit();
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            exception.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }

        return movies;
    }

    public static void insert(Movie movie) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            MovieDAO.getInstance().insert(connection, movie);
            ShowDAO.getInstance().insert(connection, movie);
            ShowDAO.getInstance().addGenresToShow(connection, movie);
            ShowDAO.getInstance().addRates(connection, movie);

            connection.commit();
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            exception.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public static void update(Movie movie) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            MovieDAO.getInstance().update(connection, movie);
            ShowDAO.getInstance().update(connection, movie);
            ShowDAO.getInstance().deleteAllShowGenres(connection, movie);
            ShowDAO.getInstance().addGenresToShow(connection, movie);

            connection.commit();
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            exception.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }


}
