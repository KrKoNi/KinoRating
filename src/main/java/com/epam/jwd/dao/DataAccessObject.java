package com.epam.jwd.dao;

import java.util.List;

public interface DataAccessObject<T> {
    List<T> readAll();
    void insert(T t);
    void update(T t);
}
