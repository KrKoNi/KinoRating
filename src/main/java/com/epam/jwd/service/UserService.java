package com.epam.jwd.service;

import com.epam.jwd.connect.BasicConnectionPool;
import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.User;
import com.epam.jwd.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type User service.
 */
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);

    private UserService() {}

    /**
     * Insert.
     *
     * @param user the user
     */
    public static void insert(User user) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            UserDAO.getInstance().insert(connection, user);
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public static List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            users = UserDAO.getInstance().readAll(connection);
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return users;
    }

    /**
     * Read with offset list.
     *
     * @param offset the offset
     * @param num    the num
     * @return the list
     */
    public static List<User> readWithOffset(int offset, int num) {
        List<User> users = new ArrayList<>();
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            users = UserDAO.getInstance().readWithOffset(connection, offset, num);
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return users;
    }

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    public static User findById(int id) {
        User user = null;
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            user = UserDAO.getInstance().findById(connection, id);

            Map<Integer, Byte> userRates = UserDAO.getInstance().getUserRates(connection, user);
            user.addRates(userRates);

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return user;
    }

    /**
     * Remove user.
     *
     * @param userId the user id
     */
    public static void removeUser(int userId) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            UserDAO.getInstance().delete(connection, userId);
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
    }

    /**
     * Find by login and password user.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     */
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
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return user;
    }

    /**
     * Find by login user.
     *
     * @param login the login
     * @return the user
     */
    public static User findByLogin(String login) {
        User user = null;
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            user = UserDAO.getInstance().findByLogin(connection, login);

            if (user == null) return null;

            Map<Integer, Byte> userRates = UserDAO.getInstance().getUserRates(connection, user);
            user.addRates(userRates);

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return user;
    }

    /**
     * Find by email user.
     *
     * @param email the email
     * @return the user
     */
    public static User findByEmail(String email) {
        User user = null;
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {

            user = UserDAO.getInstance().findByEmail(connection, email);

            if (user == null) return null;

            Map<Integer, Byte> userRates = UserDAO.getInstance().getUserRates(connection, user);
            user.addRates(userRates);

            connection.commit();
        } catch (DaoException exception) {
            logger.error("Error occurred in DAO, connection rollbacked");
        }
        return user;
    }

    /**
     * Update.
     *
     * @param user the user
     */
    public static void update(User user) {
        try (ProxyConnection connection = BasicConnectionPool.INSTANCE.getConnection()) {
            UserDAO.getInstance().update(connection, user);
        } catch (DaoException e) {
            e.printStackTrace(); //logs
        }
    }
}
