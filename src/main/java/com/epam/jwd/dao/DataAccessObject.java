package com.epam.jwd.dao;

import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject<T> {
    List<T> readAll(ProxyConnection connection) throws SQLException, DaoException;
    List<T> readWithOffset(ProxyConnection connection, int offset, int num) throws SQLException, DaoException;
    T findById(ProxyConnection connection, int id) throws SQLException, DaoException;
    void insert(ProxyConnection connection, T t) throws SQLException, DaoException;
    void update(ProxyConnection connection, T t) throws SQLException, DaoException;
    void delete(ProxyConnection connection, T t) throws SQLException, DaoException;

}
