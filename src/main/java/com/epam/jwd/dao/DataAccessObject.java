package com.epam.jwd.dao;


import java.util.ArrayList;

public interface DataAccessObject<T> {
    static void connect() {

    }
    ArrayList<T> readAll();
    void insert(T t);
    void update(T t);
}
