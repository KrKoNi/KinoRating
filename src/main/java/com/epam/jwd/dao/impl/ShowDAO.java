package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ShowDAO implements DataAccessObject<Show> {
    private static final ShowDAO INSTANCE = new ShowDAO();

    private ShowDAO() {
    }

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

    }

    @Override
    public void update(Show show) throws SQLException {

    }

    public void initShowRates(Show show) {
        Map<Integer, Byte> rates = show.getRates();
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM kinorating.kino_ratings where kino_id = ?;"
                )
        ) {
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
    }

    public void addRate(Show show, User user, int rate) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO kinorating.kino_ratings(kino_id, user_id, kino_rating) values (?, ?, ?) " +
                        "on duplicate key update kino_rating = ?"
        )) {
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
