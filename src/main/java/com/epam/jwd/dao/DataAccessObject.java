package com.epam.jwd.dao;

import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject<T> {
    List<T> readAll();
    T findById(int id);
    void insert(T t) throws SQLException;
    void update(T t) throws SQLException;

}
