package com.epam.jwd.dto;

/**
 * Data transfer object interface.
 *
 * @param <T> domain class
 */
public interface DataTransferObject<T> {
    /**
     * Gets object.
     *
     * @return the object
     */
    T getObject();
}
