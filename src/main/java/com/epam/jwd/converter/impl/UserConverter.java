package com.epam.jwd.converter.impl;

import com.epam.jwd.converter.Converter;
import com.epam.jwd.domain.User;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.service.UserService;

/**
 * User converter from UserDTO to uUser and vice versa
 */
public class UserConverter implements Converter<User, UserDTO> {
    private static final UserConverter INSTANCE = new UserConverter();

    private UserConverter() {}

    public static UserConverter getInstance() {
        return INSTANCE;
    }

    @Override
    public User toObject(UserDTO dtoObject) {
        return UserService.findById(dtoObject.getId());
    }

    @Override
    public UserDTO toDto(User object) {
        return new UserDTO(object.getId(), object.getRole());
    }
}
