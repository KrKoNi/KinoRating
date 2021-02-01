package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Genre;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.GenreService;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class ShowDAO implements DataAccessObject<Show> {

    private static final String INSERT_SQL = "INSERT INTO kinorating.abstract_kino (title, image_link, short_description, description) VALUES (?, ?, ?, ?)";
    private static final String SELECT_RATES_BY_SHOW_SQL = "SELECT * FROM kinorating.kino_ratings where kino_id = ?";
    private static final String SELECT_GENRES_BY_SHOW_SQL = "SELECT * FROM kinorating.kino_genres where kino_id = ?";
    private static final String ADD_RATE_SQL = "INSERT INTO kinorating.kino_ratings(kino_id, user_id, kino_rating) values (?, ?, ?) on duplicate key update kino_rating = ?";
    private static final String DELETE_RATE_SQL = "DELETE FROM kinorating.kino_ratings where kino_id = ? and user_id = ?";
    private static final String ADD_RATES_SQL = "INSERT INTO kinorating.kino_ratings(kino_id, user_id, kino_rating) values (?, ?, ?) on duplicate key update kino_rating = ?";
    private static final String ADD_GENRES_SQL = "INSERT INTO kinorating.kino_genres (kino_id, genre_id) VALUES (?, ?) on duplicate key update genre_id=genre_id";
    private static final String DELETE_GENRES_SQL = "DELETE FROM kinorating.kino_genres where kino_id = ?";
    private static final String UPDATE_SQL = "UPDATE kinorating.abstract_kino SET title = ?, short_description = ?, description = ?, image_link = ? where id = ?";
    private static final String DELETE_SQL = "DELETE FROM kinorating.abstract_kino where id = ?";

    private static final ShowDAO INSTANCE = new ShowDAO();

    private ShowDAO() {
    }

    private static final Logger LOGGER = Logger.getLogger(ShowDAO.class);

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
    public void insert(ProxyConnection connection, Show show) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setString(1, show.getTitle());
            statement.setString(2, show.getImageLink());
            statement.setString(3, show.getShortDescription());
            statement.setString(4, show.getDescription());

            statement.execute();

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }

    @Override
    public void update(ProxyConnection connection, Show show) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {

            statement.setString(1, show.getTitle());
            statement.setString(2, show.getShortDescription());
            statement.setString(3, show.getDescription());
            statement.setString(4, show.getImageLink());
            statement.setInt(5, show.getId());

            statement.execute();
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }

    public void delete(ProxyConnection connection, Show show) throws SQLException {
        delete(connection, show.getId());
    }

    public void delete(ProxyConnection connection, int showId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, showId);
            statement.execute();
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }

    public Map<Integer, Byte> getShowRates(ProxyConnection connection, Show show) throws SQLException {
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
            exception.printStackTrace();
            throw new SQLException(exception);
        }
        return rates;
    }

    public List<Genre> getShowGenres(ProxyConnection connection, Show show) throws SQLException {
        List<Genre> genres = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_GENRES_BY_SHOW_SQL)) {
            statement.setInt(1, show.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                genres.add(GenreService.findById(resultSet.getInt("genre_id")));
            }

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
        return genres;
    }

    public void addRate(ProxyConnection connection, Show show, User user, int rate) throws SQLException {
        addRate(connection, show.getId(), user.getId(), rate);
    }

    public void addRate(ProxyConnection connection, Show show, int userId, int rate) throws SQLException {
        addRate(connection, show.getId(), userId, rate);
    }

    public void addRate(ProxyConnection connection, int showId, int userId, int rate) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(ADD_RATE_SQL)) {

            statement.setInt(1, showId);
            statement.setInt(2, userId);
            statement.setInt(3, rate);
            statement.setInt(4, rate);

            statement.execute();

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }

    public void addRates(ProxyConnection connection, Show show) throws SQLException {
        Map<Integer, Byte> rates = show.getRates();
        try (PreparedStatement statement = connection.prepareStatement(ADD_RATES_SQL)) {
            rates.forEach((userId, rate) -> {
                try {
                    statement.setInt(1, show.getId());
                    statement.setInt(2, userId);
                    statement.setInt(3, rate);
                    statement.addBatch();
                } catch (SQLException exception) {
                    connection.rollback();
                }
            });
            statement.executeBatch();

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }

    public void removeRate(ProxyConnection connection, int showId, int userId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_RATE_SQL)) {

            statement.setInt(1, showId);
            statement.setInt(2, userId);

            statement.execute();

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }


    public void addGenresToShow(ProxyConnection connection, Show show) throws SQLException {
        List<Genre> genres = show.getGenres();
        try (PreparedStatement statement = connection.prepareStatement(ADD_GENRES_SQL)) {
            connection.setAutoCommit(false);

            for (Genre genre : genres) {
                statement.setInt(1, show.getId());
                statement.setInt(2, genre.getId());
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }

    public void deleteAllShowGenres(ProxyConnection connection, Show show) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_GENRES_SQL)) {

            statement.setInt(1, show.getId());

            statement.execute();

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }

}
