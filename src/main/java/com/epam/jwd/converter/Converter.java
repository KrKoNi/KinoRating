package com.epam.jwd.converter;

/**
 * Converter interface which converts object to corresponding dto and vice versa
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public interface Converter<K, V> {
    /**
     * Converts from DTO to corresponding object
     *
     * @param dtoObject the dto object
     * @return the k
     */
    K toObject (V dtoObject);

    /**
     * Converts from object to corresponding DTO
     *
     * @param object the object
     * @return the v
     */
    V toDto (K object);
}
