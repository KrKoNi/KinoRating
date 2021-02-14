package com.epam.jwd.connect;

import com.epam.jwd.config.DatabaseProperties;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Connection pool based on enum.
 */
public enum BasicConnectionPool {

    INSTANCE;

    private String url;
    private String user;
    private String password;

    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> usingConnections;
    private final static int INITIAL_POOL_SIZE = 15;

    private final Logger logger = Logger.getLogger(BasicConnectionPool.class);

    BasicConnectionPool() {
        url = DatabaseProperties.getUrl();
        user = DatabaseProperties.getUser();
        password = DatabaseProperties.getPassword();

        freeConnections = new LinkedBlockingQueue<>(INITIAL_POOL_SIZE);
        usingConnections = new ArrayDeque<>();

        logger.info("Connection pool initialization");
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                freeConnections.offer(new ProxyConnection(DriverManager.getConnection(url, user, password)));
                logger.info("Connection added to pool");
            } catch (SQLException exception) {
                logger.error("Error adding connection to the pool", exception);
            }
        }
        logger.info("Connection pool was initialized");

    }

    /**
     * Gets connection from pool.
     *
     * @return the connection
     */
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            logger.info("Asking for connection");
            connection = freeConnections.take();
            usingConnections.offer(connection);
            logger.info("Connection received");
            connection.setAutoCommit(false);
        } catch (InterruptedException e) {
            logger.error("Error trying to get connection from pool, try again later", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Release connection to pool.
     *
     * @param connection the connection
     */
    public void releaseConnection(ProxyConnection connection) {
        usingConnections.remove(connection);
        freeConnections.offer(connection);
        logger.info("Connection returned to pool");
    }

    /**
     * Destroy pool.
     */
    public void destroyPool() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                freeConnections.take().realClose();
            } catch (InterruptedException exception) {
                logger.error("Cannot close connection", exception);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
        logger.info("Pool was destroyed");
    }
    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException exception) {
                logger.error("Error occurred when deregister drivers", exception);
            }
        });
    }

}
