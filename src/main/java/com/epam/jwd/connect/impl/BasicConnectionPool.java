package com.epam.jwd.connect.impl;

import com.epam.jwd.config.ApplicationProperties;
import com.epam.jwd.connect.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class BasicConnectionPool implements ConnectionPool {
    private String url;
    private String user;
    private String password;
    private Queue<Connection> connectionPool;
    private Queue<Connection> usedConnections = new LinkedList<>();
    private static int INITIAL_POOL_SIZE = 10;


    private static final BasicConnectionPool INSTANCE = create(
            ApplicationProperties.getUrl(),
            ApplicationProperties.getUser(),
            ApplicationProperties.getPassword()
    );

    public static BasicConnectionPool getInstance() {
        return INSTANCE;
    }

    private static BasicConnectionPool create(String url, String user, String password) {
        Queue<Connection> pool = new LinkedList<>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }

        return new BasicConnectionPool(url, user, password, pool);
    }

    private BasicConnectionPool(String url, String user, String password, Queue<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove();
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) {
        Connection connection = null;
        System.out.println(DriverManager.getDrivers());
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }
}