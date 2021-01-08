package com.epam.jwd.converter;

public interface Converter<K, V> {
    K toObject (V dtoObject);
    V toDto (K object);
}
