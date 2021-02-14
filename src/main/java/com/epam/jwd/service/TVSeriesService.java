package com.epam.jwd.service;

import com.epam.jwd.connect.BasicConnectionPool;
import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Tv series service.
 */
public class TVSeriesService {

    private static final Logger logger = Logger.getLogger(TVSeriesService.class);

    /**
     * Find by id tv series.
     *
     * @param id the id
     * @return the tv series
     * @throws SQLException the sql exception
     */
    public static TVSeries findById(int id) throws SQLException {
        TVSeries tvSeries = null;
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            tvSeries = TVSeriesDAO.getInstance().findById(connection, id);
            if (tvSeries == null) {
                return null;
            }
            tvSeries.addGenres(ShowDAO.getInstance().getShowGenres(connection, tvSeries));

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }

        return tvSeries;
    }

    /**
     * Read with offset list.
     *
     * @param offset the offset
     * @param num    the num
     * @return the list
     * @throws SQLException the sql exception
     */
    public static List<TVSeries> readWithOffset(int offset, int num) throws SQLException {

        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            tvSeriesList = TVSeriesDAO.getInstance().readWithOffset(connection, offset, num);
            for (TVSeries tvSeries : tvSeriesList) {
                tvSeries.addGenres(ShowDAO.getInstance().getShowGenres(connection, tvSeries));

            }

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }

        return tvSeriesList;
    }

    /**
     * Insert.
     *
     * @param tvSeries the tv series
     */
    public static void insert(TVSeries tvSeries) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            ShowDAO.getInstance().insert(connection, tvSeries);
            TVSeriesDAO.getInstance().insert(connection, tvSeries);
            int id = ShowDAO.getInstance().getLastInsertedId(connection);
            tvSeries.setId(id);
            ShowDAO.getInstance().addGenresToShow(connection, tvSeries);

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
    }

    /**
     * Update.
     *
     * @param tvSeries the tv series
     */
    public static void update(TVSeries tvSeries) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            TVSeriesDAO.getInstance().update(connection, tvSeries);
            ShowDAO.getInstance().update(connection, tvSeries);
            ShowDAO.getInstance().deleteAllShowGenres(connection, tvSeries);
            ShowDAO.getInstance().addGenresToShow(connection, tvSeries);

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
    }

    /**
     * Gets tv count.
     *
     * @return the tv count
     */
    public static int getTVCount() {
        int rowCount = 0;

        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            rowCount = TVSeriesDAO.getInstance().getRowCount(connection);

            connection.commit();

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
    public static List<TVSeries> sortByWithOffset(String sortBy, String order, int offset, int number) {
        List<TVSeries> tvSeries = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            tvSeries.addAll(TVSeriesDAO.getInstance().getShowsSortedBy(connection, sortBy, order, offset, number));

        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return tvSeries;
    }

}
