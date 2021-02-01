package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;

import java.sql.*;
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
    private static final String DELETE_SQL = "DELETE FROM kinorating.users where id = ?";
    private static final String SELECT_USER_RATES_SQL = "SELECT * FROM kinorating.kino_ratings where user_id = ?";

    @Override
    public List<User> readAll(ProxyConnection connection) throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = setFieldsFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
        return users;
    }

    @Override
    public List<User> readWithOffset(ProxyConnection connection, int offset, int num) throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_WITH_OFFSET_SQL)) {
            statement.setInt(1, offset);
            statement.setInt(2, num);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = setFieldsFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }

        return users;
    }

    @Override
    public User findById(ProxyConnection connection, int id) throws SQLException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = setFieldsFromResultSet(resultSet);
            } else {
                throw new RuntimeException("User not found");//todo: fix
            }

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
        return user;
    }

    @Override
    public void insert(ProxyConnection connection, User user) throws SQLException {
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

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }

    public void delete(ProxyConnection connection, int userId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {

            statement.setInt(1, userId);

            statement.execute();

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }


    @Override
    public void update(ProxyConnection connection, User user) {

    }

    @Override
    public void delete(ProxyConnection connection, User user) throws SQLException {

    }

    public User findByLoginAndPassword(ProxyConnection connection, String login, String password) throws SQLException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD_SQL)) {

            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = setFieldsFromResultSet(resultSet);
            }

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
        return user;
    }

    public Map<Integer, Byte> getUserRates(ProxyConnection connection, User user) throws SQLException {
        Map<Integer, Byte> rates = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_RATES_SQL)) {
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int showId = resultSet.getInt("kino_id");
                byte rate = (byte) resultSet.getInt("kino_rating");
                rates.put(showId, rate);
            }

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
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
