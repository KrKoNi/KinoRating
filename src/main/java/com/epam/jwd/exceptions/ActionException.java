package com.epam.jwd.exceptions;

/**
 * Action exception.
 */
public class ActionException extends Exception {
    /**
     * Instantiates a new Action exception.
     *
     * @param message the message
     */
    public ActionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Action exception.
     *
     * @param message   the message
     * @param exception the exception
     */
    public ActionException(String message, Throwable exception) {
        super(message, exception);
    }

    /**
     * Instantiates a new Action exception.
     *
     * @param exception the exception
     */
    public ActionException(Throwable exception) {
        super(exception);
    }
}
