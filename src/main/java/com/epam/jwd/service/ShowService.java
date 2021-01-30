package com.epam.jwd.service;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.TVSeries;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowService {
    private static final Logger logger = Logger.getLogger(ShowService.class);

    private ShowService() {}

    public static List<Show> findLike(String str) {
        List<Show> showList = new ArrayList<>();
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            List<Movie> movies = MovieDAO.getInstance().findLike(connection, str);
            List<TVSeries> tvSeriesList = TVSeriesDAO.getInstance().findLike(connection, str);

            showList.addAll(movies);
            showList.addAll(tvSeriesList);

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

        return showList;
    }

    public static void addRate(Show show, int userId, int rate) {
        addRate(show.getId(), userId, rate);
    }

    public static void addRate(int showId, int userId, int rate) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            ShowDAO.getInstance().addRate(connection, showId, userId, rate);
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public static void removeRate(int showId, int userId) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            ShowDAO.getInstance().removeRate(connection, showId, userId);
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public static void removeShow(int showId) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            ShowDAO.getInstance().delete(connection, showId);
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

}
