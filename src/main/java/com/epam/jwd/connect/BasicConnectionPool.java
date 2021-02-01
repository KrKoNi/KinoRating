package com.epam.jwd.connect;

import com.epam.jwd.config.DatabaseProperties;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public enum BasicConnectionPool {
    INSTANCE;

    private String url;
    private String user;
    private String password;

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> usingConnections;
    private final static int INITIAL_POOL_SIZE = 15;

    private final Logger logger = Logger.getLogger(BasicConnectionPool.class);

    BasicConnectionPool() {
        url = DatabaseProperties.getUrl();
        user = DatabaseProperties.getUser();
        password = DatabaseProperties.getPassword();

        freeConnections = new LinkedBlockingQueue<>(INITIAL_POOL_SIZE);
        usingConnections = new ArrayDeque<>();

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                freeConnections.offer(new ProxyConnection(DriverManager.getConnection(url, user, password)));
            } catch (SQLException exception) {
                logger.error("Error adding connection to the pool", exception);
                exception.printStackTrace();
            }
        }

    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usingConnections.offer(connection);
            connection.setAutoCommit(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        usingConnections.remove(connection);
        freeConnections.offer(connection);
    }

    public void destroyPool() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                freeConnections.take().realClose();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }

}
