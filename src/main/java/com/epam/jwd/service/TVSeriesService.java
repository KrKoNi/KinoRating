package com.epam.jwd.service;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.domain.TVSeries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TVSeriesService {
    public static TVSeries findById(int id) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        TVSeries tvSeries = null;
        try {
            connection.setAutoCommit(false);

            tvSeries = TVSeriesDAO.getInstance().findById(connection, id);
            tvSeries.addGenres(ShowDAO.getInstance().getShowGenres(connection, tvSeries));
            tvSeries.addRates(ShowDAO.getInstance().getShowRates(connection, tvSeries));

            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }

        return tvSeries;
    }

    public static List<TVSeries> readWithOffset(int offset, int num) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);

            tvSeriesList = TVSeriesDAO.getInstance().readWithOffset(connection, offset, num);
            for (TVSeries tvSeries : tvSeriesList) {
                tvSeries.addGenres(ShowDAO.getInstance().getShowGenres(connection, tvSeries));
                tvSeries.addRates(ShowDAO.getInstance().getShowRates(connection, tvSeries));
            }

            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }

        return tvSeriesList;
    }

    public static void insert(TVSeries tvSeries) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            TVSeriesDAO.getInstance().insert(connection, tvSeries);
            ShowDAO.getInstance().insert(connection, tvSeries);
            ShowDAO.getInstance().addGenresToShow(connection, tvSeries);
            ShowDAO.getInstance().addRates(connection, tvSeries);

            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public static void update(TVSeries tvSeries) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            TVSeriesDAO.getInstance().update(connection, tvSeries);
            ShowDAO.getInstance().update(connection, tvSeries);
            ShowDAO.getInstance().deleteAllShowGenres(connection, tvSeries);
            ShowDAO.getInstance().addGenresToShow(connection, tvSeries);

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
