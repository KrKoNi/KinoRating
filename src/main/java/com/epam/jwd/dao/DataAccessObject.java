package com.epam.jwd.dao;

import com.epam.jwd.connect.ProxyConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject<T> {
    List<T> readAll(ProxyConnection connection) throws SQLException;
    List<T> readWithOffset(ProxyConnection connection, int offset, int num) throws SQLException;
    T findById(ProxyConnection connection, int id) throws SQLException;
    void insert(ProxyConnection connection, T t) throws SQLException;
    void update(ProxyConnection connection, T t) throws SQLException;
    void delete(ProxyConnection connection, T t) throws SQLException;

}
