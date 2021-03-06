package com.epam.jwd.exceptions;

/**
 * Dao exception.
 */
public class DaoException extends Exception {
    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param message   the message
     * @param exception the exception
     */
    public DaoException(String message, Throwable exception) {
        super(message, exception);
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param exception the exception
     */
    public DaoException(Throwable exception) {
        super(exception);
    }
}
