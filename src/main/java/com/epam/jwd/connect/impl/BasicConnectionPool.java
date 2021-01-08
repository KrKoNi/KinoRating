package com.epam.jwd.connect.impl;

import com.epam.jwd.config.DatabaseProperties;
import com.epam.jwd.connect.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BasicConnectionPool implements ConnectionPool {
    private final String url;
    private final String user;
    private final String password;

    private final BlockingQueue<Connection> connectionPool;
    private final BlockingQueue<Connection> usedConnections = new LinkedBlockingQueue<>();
    private static int INITIAL_POOL_SIZE = 10;

    private static final BasicConnectionPool INSTANCE = create(
            DatabaseProperties.getUrl(),
            DatabaseProperties.getUser(),
            DatabaseProperties.getPassword()
    );

    public static BasicConnectionPool getInstance() {
        return INSTANCE;
    }

    private static BasicConnectionPool create(String url, String user, String password) {
        BlockingQueue<Connection> pool = new LinkedBlockingQueue<>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }

        return new BasicConnectionPool(url, user, password, pool);
    }

    private BasicConnectionPool(String url, String user, String password, BlockingQueue<Connection> connectionPool) {
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
