package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO implements DataAccessObject<User> {

    private static final UserDAO INSTANCE = new UserDAO();

    private UserDAO() {}

    public static UserDAO getInstance() {
        return INSTANCE;
    }

    private static final String SELECT_SQL = "SELECT * FROM kinorating.users natural join kinorating.user_role";
    private static final String SELECT_WITH_OFFSET_SQL = "SELECT * FROM kinorating.users natural join kinorating.user_role LIMIT ?, ?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM kinorating.users where id = ? limit 1";
    private static final String SELECT_BY_LOGIN_AND_PASSWORD_SQL = "SELECT * FROM kinorating.users where login = ? and password = ? limit 1";
    private static final String INSERT_SQL = "INSERT INTO kinorating.users (first_name, last_name, birth_date, email, login, password, registration_date, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_RATES_SQL = "SELECT * FROM kinorating.kino_ratings where user_id = ?";

    @Override
    public List<User> readAll(Connection connection) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = setFieldsFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> readWithOffset(Connection connection, int offset, int num) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_WITH_OFFSET_SQL)) {
            statement.setInt(1, offset);
            statement.setInt(2, num);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = setFieldsFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    @Override
    public User findById(Connection connection, int id) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = setFieldsFromResultSet(resultSet);
            } else {
                throw new RuntimeException("User not found");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void insert(Connection connection, User user) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)
        ) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, Date.valueOf(user.getBirthDate()));
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getLogin());
            statement.setString(6, user.getPassword());
            statement.setDate(7, Date.valueOf(user.getRegistrationDate()));
            statement.setInt(8, user.getRole().getId());

            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Connection connection, User user) {

    }

    public User findByLoginAndPassword(Connection connection, String login, String password) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD_SQL)) {

            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = setFieldsFromResultSet(resultSet);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public Map<Integer, Byte> getUserRates(Connection connection, User user) {
        Map<Integer, Byte> rates = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_RATES_SQL)) {
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int showId = resultSet.getInt("kino_id");
                byte rate = (byte) resultSet.getInt("kino_rating");
                rates.put(showId, rate);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rates;
    }

    private User setFieldsFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        user.setRegistrationDate(resultSet.getDate("registration_date").toLocalDate());
        user.setRole(Role.getById(resultSet.getInt("role_id")));

        return user;
    }


}
