package com.epam.jwd.converter.impl;

import com.epam.jwd.converter.Converter;
import com.epam.jwd.domain.User;
import com.epam.jwd.dto.impl.UserDTO;

public class UserConverter implements Converter<User, UserDTO> {
    private static final UserConverter INSTANCE = new UserConverter();

    private UserConverter() {}

    public static UserConverter getInstance() {
        return INSTANCE;
    }

    @Override
    public User toObject(UserDTO dtoObject) {
        return null;
    }

    @Override
    public UserDTO toDto(User object) {
        return new UserDTO(object.getId(), object.getRole());
    }
}
