package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowDAO implements DataAccessObject<Show> {

    private static final String INSERT_SQL = "INSERT INTO kinorating.abstract_kino (title, image_link) VALUES (?, ?)";
    private static final String SELECT_RATES_BY_SHOW_SQL = "SELECT * FROM kinorating.kino_ratings where kino_id = ?";
    private static final String ADD_RATE_SQL = "INSERT INTO kinorating.kino_ratings(kino_id, user_id, kino_rating) values (?, ?, ?) on duplicate key update kino_rating = ?";

    private static final ShowDAO INSTANCE = new ShowDAO();

    private ShowDAO() {
    }

    private static final Logger logger = Logger.getLogger(ShowDAO.class);

    public static ShowDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Show> readAll() {
        return null;
    }

    @Override
    public List<Show> readWithOffset(int offset, int num) {
        return null;
    }

    @Override
    public Show findById(int id) {
        return null;
    }

    @Override
    public void insert(Show show) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setString(1, show.getTitle());
            statement.setString(2, show.getImageLink());

            statement.execute();

        } catch (SQLException exception) {
            System.out.println("error");
            logger.error("Error trying to insert a movie into database", exception);
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void update(Show show) throws SQLException {
        return;
    }

    public Map<Integer, Byte> getShowRates(Show show) {
        Map<Integer, Byte> rates = new HashMap<>();
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_RATES_BY_SHOW_SQL)) {

            statement.setInt(1, show.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                byte rate = (byte) resultSet.getInt("kino_rating");

                rates.put(userId, rate);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return rates;
    }

    public void addRate(Show show, User user, int rate) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_RATE_SQL)) {

            statement.setInt(1, show.getId());
            statement.setInt(2, user.getId());
            statement.setInt(3, rate);
            statement.setInt(4, rate);

            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }


}
