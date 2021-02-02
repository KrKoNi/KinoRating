package com.epam.jwd.exceptions;

public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable exception) {
        super(message, exception);
    }

    public DaoException(Throwable exception) {
        super(exception);
    }
}
