package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Genre;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.User;
import com.epam.jwd.exceptions.DaoException;
import com.epam.jwd.service.GenreService;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Show DAO
 */
public final class ShowDAO implements DataAccessObject<Show> {

    private static final String INSERT_SQL = "INSERT INTO kinorating.abstract_kino (title, image_link, short_description, description) VALUES (?, ?, ?, ?)";
    private static final String SELECT_RATES_BY_SHOW_SQL = "SELECT * FROM kinorating.kino_ratings where kino_id = ?";
    private static final String SELECT_LAST_INSERTED_ID = "SELECT LAST_INSERT_ID() FROM kinorating.abstract_kino";
    private static final String SELECT_GENRES_BY_SHOW_SQL = "SELECT * FROM kinorating.kino_genres where kino_id = ?";
    private static final String SELECT_AVERAGE_SHOW_RATE_SQL = "SELECT AVG(kino_ratings.kino_rating) FROM kinorating.kino_ratings where kino_id = ?";
    private static final String ADD_RATE_SQL = "INSERT INTO kinorating.kino_ratings(kino_id, user_id, kino_rating) values (?, ?, ?) on duplicate key update kino_rating = ?";
    private static final String DELETE_RATE_SQL = "DELETE FROM kinorating.kino_ratings where kino_id = ? and user_id = ?";
    private static final String ADD_GENRES_SQL = "INSERT INTO kinorating.kino_genres (kino_id, genre_id) VALUES (?, ?) on duplicate key update genre_id=genre_id";
    private static final String DELETE_GENRES_SQL = "DELETE FROM kinorating.kino_genres where kino_id = ?";
    private static final String UPDATE_SQL = "UPDATE kinorating.abstract_kino SET title = ?, short_description = ?, description = ?, image_link = ? where id = ?";
    private static final String SET_AVERAGE_RATE_SQL = "UPDATE kinorating.abstract_kino SET rate = ? where id = ?";
    private static final String DELETE_SQL = "DELETE FROM kinorating.abstract_kino where id = ?";
    private static final String SELECT_LIKE_WITH_OFFSET_SQL = "SELECT abstract_kino.id FROM kinorating.abstract_kino natural join kinorating.movies, kinorating.tv_series where title like ? limit ?, ?";
    private static final String SELECT_LIKE_SQL = "SELECT abstract_kino.id FROM kinorating.abstract_kino where title like ? LIMIT 120";
    private static final String SELECT_ROW_COUNT_SQL = "SELECT COUNT(abstract_kino.id) FROM kinorating.abstract_kino";
    private static final String SELECT_SHOW_RATE_BY_USER_SQL = "SELECT * FROM kinorating.kino_ratings where user_id = ? and kino_id = ?";

    private static final ShowDAO INSTANCE = new ShowDAO();

    private ShowDAO() {
    }

    private static final Logger logger = Logger.getLogger(ShowDAO.class);

    public static ShowDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Show> readAll(ProxyConnection connection) {
        throw new RuntimeException("Unsupported operation");
    }

    @Override
    public List<Show> readWithOffset(ProxyConnection connection, int offset, int num) {
        throw new RuntimeException("Unsupported operation");
    }

    @Override
    public Show findById(ProxyConnection connection, int id) {
        throw new RuntimeException("Unsupported operation");
    }

    @Override
    public void insert(ProxyConnection connection, Show show) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setString(1, show.getTitle());
            statement.setString(2, show.getImageLink());
            statement.setString(3, show.getShortDescription());
            statement.setString(4, show.getDescription());

            statement.execute();

        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
    }

    @Override
    public void update(ProxyConnection connection, Show show) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {

            statement.setString(1, show.getTitle());
            statement.setString(2, show.getShortDescription());
            statement.setString(3, show.getDescription());
            statement.setString(4, show.getImageLink());
            statement.setInt(5, show.getId());

            statement.execute();
        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
    }

    public void delete(ProxyConnection connection, Show show) throws DaoException {
        delete(connection, show.getId());
    }

    /**
     * Delete show from database.
     *
     * @param connection proxy connection
     * @param showId     show id
     * @throws DaoException the dao exception
     */
    public void delete(ProxyConnection connection, int showId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, showId);
            statement.execute();
        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
    }

    /**
     * Gets all show rates.
     *
     * @param connection the connection
     * @param show       the show
     * @return show rates
     * @throws DaoException the dao exception
     */
    public Map<Integer, Byte> getShowRates(ProxyConnection connection, Show show) throws DaoException {
        Map<Integer, Byte> rates = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_RATES_BY_SHOW_SQL)) {

            statement.setInt(1, show.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                byte rate = (byte) resultSet.getInt("kino_rating");

                rates.put(userId, rate);
            }

        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
        return rates;
    }

    /**
     * Gets the show rate by user id.
     *
     * @param connection proxy connection
     * @param showId     the show id
     * @param userId     the user id
     * @return user rate
     * @throws DaoException the dao exception
     */
    public int getShowRateByUserId(ProxyConnection connection, int showId, int userId) throws DaoException {

        int showRate = 0;

        try (PreparedStatement statement = connection.prepareStatement(SELECT_SHOW_RATE_BY_USER_SQL)) {

            statement.setInt(1, userId);
            statement.setInt(2, showId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                showRate = resultSet.getInt("kino_rating");
            }

        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
        return showRate;
    }

    /**
     * Returns number of all shows in abstract_kino table
     *
     * @param connection proxy connection
     * @return number of shows
     * @throws DaoException the dao exception
     */
    public int getRowCount(ProxyConnection connection) throws DaoException {
        int rowCount = 0;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ROW_COUNT_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
        return rowCount;
    }

    /**
     * Find shows with offset where title contains @param str
     *
     * @param connection the connection
     * @param str        the str
     * @param offset     the offset
     * @param number     the number
     * @return the list
     * @throws DaoException the dao exception
     */
    public List<Show> findLikeWithOffset(ProxyConnection connection, String str, int offset, int number) throws DaoException {
        List<Show> showList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LIKE_WITH_OFFSET_SQL)) {
            statement.setString(1, "%" + str + "%");
            statement.setInt(2, offset);
            statement.setInt(3, number);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Show show = null;

                int id = resultSet.getInt("id");

                if (MovieDAO.getInstance().isMovie(connection, id)) {
                    show = MovieDAO.getInstance().findById(connection, id);
                } else if (TVSeriesDAO.getInstance().isTV(connection, id)) {
                    show = TVSeriesDAO.getInstance().findById(connection, id);
                }

                showList.add(show);

            }
        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
        return showList;
    }

    /**
     * Find all shows where title contains @param str
     *
     * @param connection proxy connection
     * @param str        str
     * @return show list
     * @throws DaoException the dao exception
     */
    public List<Show> findLike(ProxyConnection connection, String str) throws DaoException {
        List<Show> showList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LIKE_SQL)) {

            statement.setString(1, "%" + str + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Show show = null;
                int id = resultSet.getInt("id");
                if (MovieDAO.getInstance().isMovie(connection, id)) {
                    show = MovieDAO.getInstance().findById(connection, id);
                } else if (TVSeriesDAO.getInstance().isTV(connection, id)) {
                    show = TVSeriesDAO.getInstance().findById(connection, id);
                }
                showList.add(show);
            }
        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
        return showList;
    }

    /**
     * Gets show genres.
     *
     * @param connection ptoxy connection
     * @param show       show
     * @return show genres
     * @throws DaoException the dao exception
     */
    public List<Genre> getShowGenres(ProxyConnection connection, Show show) throws DaoException {
        List<Genre> genres = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_GENRES_BY_SHOW_SQL)) {
            statement.setInt(1, show.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                genres.add(GenreService.findById(resultSet.getInt("genre_id")));
            }

        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
        return genres;
    }

    /**
     * Add rate.
     *
     * @param connection the connection
     * @param show       the show
     * @param user       the user
     * @param rate       the rate
     * @throws DaoException the dao exception
     */
    public void addRate(ProxyConnection connection, Show show, User user, int rate) throws DaoException {
        addRate(connection, show.getId(), user.getId(), rate);
    }

    /**
     * Add rate.
     *
     * @param connection the connection
     * @param show       the show
     * @param userId     the user id
     * @param rate       the rate
     * @throws DaoException the dao exception
     */
    public void addRate(ProxyConnection connection, Show show, int userId, int rate) throws DaoException {
        addRate(connection, show.getId(), userId, rate);
    }

    /**
     * Add rate.
     *
     * @param connection the connection
     * @param showId     the show id
     * @param userId     the user id
     * @param rate       the rate
     * @throws DaoException the dao exception
     */
    public void addRate(ProxyConnection connection, int showId, int userId, int rate) throws DaoException {

        try (PreparedStatement statement = connection.prepareStatement(ADD_RATE_SQL)) {

            statement.setInt(1, showId);
            statement.setInt(2, userId);
            statement.setInt(3, rate);
            statement.setInt(4, rate);

            statement.execute();

        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
    }

    /**
     * Remove corresponding rate from database.
     *
     * @param connection proxy connection
     * @param showId     show id
     * @param userId     user id
     * @throws DaoException the dao exception
     */
    public void removeRate(ProxyConnection connection, int showId, int userId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_RATE_SQL)) {

            statement.setInt(1, showId);
            statement.setInt(2, userId);

            statement.execute();

        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
    }

    /**
     * Add genres to show.
     *
     * @param connection connection
     * @param show       show
     * @throws DaoException the dao exception
     */
    public void addGenresToShow(ProxyConnection connection, Show show) throws DaoException {
        List<Genre> genres = show.getGenres();
        try (PreparedStatement statement = connection.prepareStatement(ADD_GENRES_SQL)) {

            for (Genre genre : genres) {
                statement.setInt(1, show.getId());
                statement.setInt(2, genre.getId());
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
    }

    /**
     * Gets average rate of the show.
     *
     * @param connection the connection
     * @param showId     the show id
     * @return the average rate
     * @throws DaoException the dao exception
     */
    public double getAverageRate(ProxyConnection connection, int showId) throws DaoException {
        double averageRate = 0;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_AVERAGE_SHOW_RATE_SQL)) {

            statement.setInt(1, showId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                averageRate = resultSet.getDouble(1);
            }

        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
        return averageRate;
    }

    /**
     * Sets average rate.
     *
     * @param connection the connection
     * @param showId     the show id
     * @param rate       the rate
     * @return the average rate
     * @throws DaoException the dao exception
     */
    public double setAverageRate(ProxyConnection connection, int showId, double rate) throws DaoException {
        double averageRate = 0;
        try (PreparedStatement statement = connection.prepareStatement(SET_AVERAGE_RATE_SQL)) {

            statement.setDouble(1, rate);
            statement.setInt(2, showId);

            statement.executeUpdate();

            connection.commit();

        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
        return averageRate;
    }

    /**
     * Delete all show genres.
     *
     * @param connection the connection
     * @param show       the show
     * @throws DaoException the dao exception
     */
    public void deleteAllShowGenres(ProxyConnection connection, Show show) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_GENRES_SQL)) {

            statement.setInt(1, show.getId());

            statement.execute();

        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
    }

    /**
     * Gets last inserted id.
     *
     * @param connection the connection
     * @return the last inserted id
     * @throws DaoException the dao exception
     */
    public int getLastInsertedId(ProxyConnection connection) throws DaoException {
        int id = -1;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LAST_INSERTED_ID)) {
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            connection.rollback();
            logger.error(exception);
            throw new DaoException(exception);
        }
        return id;
    }

}
