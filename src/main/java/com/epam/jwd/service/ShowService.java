package com.epam.jwd.service;

import com.epam.jwd.connect.BasicConnectionPool;
import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ShowService {
    private static final Logger logger = Logger.getLogger(ShowService.class);

    private ShowService() {}

    public static List<Show> findLike(String str) {
        List<Show> showList = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            showList.addAll(ShowDAO.getInstance().findLike(connection, str));

        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return showList;
    }

    public static List<Show> findLikeWithOffset(String str, int offset, int number) {
        List<Show> showList = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            showList.addAll(ShowDAO.getInstance().findLikeWithOffset(connection, str, offset, number));

        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return showList;
    }

    public static int getShowsCount() {
        int rowCount = 0;
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            rowCount = ShowDAO.getInstance().getRowCount(connection);
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return rowCount;
    }



    public static void addRate(Show show, int userId, int rate) {
        addRate(show.getId(), userId, rate);
    }

    public static void addRate(int showId, int userId, int rate) {
        try(ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            ShowDAO.getInstance().addRate(connection, showId, userId, rate);
            ShowDAO.getInstance().setAverageRate(connection, showId, ShowDAO.getInstance().getAverageRate(connection, showId));
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
    }

    public static void removeRate(int showId, int userId) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            ShowDAO.getInstance().removeRate(connection, showId, userId);
            ShowDAO.getInstance().setAverageRate(connection, showId, ShowDAO.getInstance().getAverageRate(connection, showId));
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
    }

    public static double getAverageRate(int showId) {
        double avgRate = 0;
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            avgRate = ShowDAO.getInstance().getAverageRate(connection, showId);
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return avgRate;
    }

    public static void removeShow(int showId) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            ShowDAO.getInstance().delete(connection, showId);
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
    }

    public static Class<? extends Show> getShowType(int showID) throws ClassNotFoundException {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            if(MovieDAO.getInstance().isMovie(connection, showID)) {
                return Movie.class;
            } else if (TVSeriesDAO.getInstance().isTV(connection, showID)) {
                return TVSeries.class;
            } else {
                throw new ClassNotFoundException("Show neither Movie nor TV");
            }
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return null;
    }

}
