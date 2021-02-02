package com.epam.jwd.service;

import com.epam.jwd.connect.BasicConnectionPool;
import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.User;
import com.epam.jwd.exceptions.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {

    private UserService() {}

    public static void insert(User user) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            UserDAO.getInstance().insert(connection, user);
        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }
    }


    public static List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            users = UserDAO.getInstance().readAll(connection);
        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }
        return users;
    }

    public static List<User> readWithOffset(int offset, int num) {
        List<User> users = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            users = UserDAO.getInstance().readWithOffset(connection, offset, num);
        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }
        return users;
    }


    public static User findById(int id) {
        User user = null;
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            user = UserDAO.getInstance().findById(connection, id);

            Map<Integer, Byte> userRates = UserDAO.getInstance().getUserRates(connection, user);
            user.addRates(userRates);

            connection.commit();
        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }
        return user;
    }

    public static void removeUser(int userId) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            UserDAO.getInstance().delete(connection, userId);
        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }
    }

    public static User findByLoginAndPassword(String login, String password) {
        User user = null;
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            user = UserDAO.getInstance().findByLoginAndPassword(connection, login, password);

            if(user == null) {
                return null;
            }

            Map<Integer, Byte> userRates = UserDAO.getInstance().getUserRates(connection, user);
            user.addRates(userRates);

            connection.commit();
        } catch (DaoException exception) {
            exception.printStackTrace(); //logs
        }
        return user;
    }
}
