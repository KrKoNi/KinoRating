package com.epam.jwd.service;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {

    private UserService() {}

    public static void insert(User user) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            UserDAO.getInstance().insert(connection, user);

            //todo add user rates
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }


    public static List<User> findAll() {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<User> users = new ArrayList<>();
        try {
            users = UserDAO.getInstance().readAll(connection);
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return users;
    }

    public static List<User> readWithOffset(int offset, int num) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<User> users;
        try {
            users = UserDAO.getInstance().readWithOffset(connection, offset, num);
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return users;
    }


    public static User findById(int id) {
        User user = null;
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            user = UserDAO.getInstance().findById(connection, id);
            Map<Integer, Byte> userRates = UserDAO.getInstance().getUserRates(connection, user);
            user.addRates(userRates);

            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
    }

    public static void removeUser(int userId) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            UserDAO.getInstance().delete(connection, userId);
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public static User findByLoginAndPassword(String login, String password) {
        User user = null;
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            user = UserDAO.getInstance().findByLoginAndPassword(connection, login, password);
            Map<Integer, Byte> userRates = UserDAO.getInstance().getUserRates(connection, user);
            user.addRates(userRates);

            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
    }
}
