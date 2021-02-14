package com.epam.jwd.dao;

import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;

/**
 * Data access object interface.
 *
 * @param <T> domain class
 */
public interface DataAccessObject<T> {
    /**
     * Read all corresponding objects from database.
     *
     * @param connection the connection
     * @return the list
     * @throws SQLException the sql exception
     * @throws DaoException the dao exception
     */
    List<T> readAll(ProxyConnection connection) throws SQLException, DaoException;

    /**
     * Read all corresponding objects from database with offset.
     *
     * @param connection the connection
     * @param offset     the offset
     * @param num        the num
     * @return the list
     * @throws SQLException the sql exception
     * @throws DaoException the dao exception
     */
    List<T> readWithOffset(ProxyConnection connection, int offset, int num) throws SQLException, DaoException;

    /**
     * Find object by id.
     *
     * @param connection the connection
     * @param id         the id
     * @return the t
     * @throws SQLException the sql exception
     * @throws DaoException the dao exception
     */
    T findById(ProxyConnection connection, int id) throws SQLException, DaoException;

    /**
     * Insert object into database.
     *
     * @param connection the connection
     * @param t          the t
     * @throws SQLException the sql exception
     * @throws DaoException the dao exception
     */
    void insert(ProxyConnection connection, T t) throws SQLException, DaoException;

    /**
     * Update object in database.
     *
     * @param connection the connection
     * @param t          the t
     * @throws SQLException the sql exception
     * @throws DaoException the dao exception
     */
    void update(ProxyConnection connection, T t) throws SQLException, DaoException;

    /**
     * Delete object from database.
     *
     * @param connection the connection
     * @param t          the t
     * @throws SQLException the sql exception
     * @throws DaoException the dao exception
     */
    void delete(ProxyConnection connection, T t) throws SQLException, DaoException;

}
