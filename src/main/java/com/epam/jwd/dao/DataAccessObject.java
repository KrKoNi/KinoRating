package com.epam.jwd.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject<T> {
    List<T> readAll(Connection connection) throws SQLException;
    List<T> readWithOffset(Connection connection, int offset, int num) throws SQLException;
    T findById(Connection connection, int id) throws SQLException;
    void insert(Connection connection, T t) throws SQLException;
    void update(Connection connection, T t) throws SQLException;

}
