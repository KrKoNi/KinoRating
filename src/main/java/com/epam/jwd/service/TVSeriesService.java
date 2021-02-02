package com.epam.jwd.service;

import com.epam.jwd.connect.BasicConnectionPool;
import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.domain.TVSeries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TVSeriesService {

    public static TVSeries findById(int id) throws SQLException {
        TVSeries tvSeries = null;
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            tvSeries = TVSeriesDAO.getInstance().findById(connection, id);
            if (tvSeries == null) {
                return null;
            }
            tvSeries.addGenres(ShowDAO.getInstance().getShowGenres(connection, tvSeries));
            tvSeries.addRates(ShowDAO.getInstance().getShowRates(connection, tvSeries));

            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace(); //logs
        }

        return tvSeries;
    }

    public static List<TVSeries> readWithOffset(int offset, int num) throws SQLException {

        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            tvSeriesList = TVSeriesDAO.getInstance().readWithOffset(connection, offset, num);
            for (TVSeries tvSeries : tvSeriesList) {
                tvSeries.addGenres(ShowDAO.getInstance().getShowGenres(connection, tvSeries));
                tvSeries.addRates(ShowDAO.getInstance().getShowRates(connection, tvSeries));
            }

            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace(); //logs
        }

        return tvSeriesList;
    }

    public static void insert(TVSeries tvSeries) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            ShowDAO.getInstance().insert(connection, tvSeries);
            TVSeriesDAO.getInstance().insert(connection, tvSeries);
            ShowDAO.getInstance().addGenresToShow(connection, tvSeries);
            ShowDAO.getInstance().addRates(connection, tvSeries);

            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace(); //logs
        }
    }

    public static void update(TVSeries tvSeries) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            TVSeriesDAO.getInstance().update(connection, tvSeries);
            ShowDAO.getInstance().update(connection, tvSeries);
            ShowDAO.getInstance().deleteAllShowGenres(connection, tvSeries);
            ShowDAO.getInstance().addGenresToShow(connection, tvSeries);

            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace(); //logs
        }
    }

    public static int getTVCount() {
        int rowCount = 0;

        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            rowCount = TVSeriesDAO.getInstance().getRowCount(connection);

            connection.commit();

        } catch (SQLException exception) {
            exception.printStackTrace(); //logs
        }

        return rowCount;
    }


}
