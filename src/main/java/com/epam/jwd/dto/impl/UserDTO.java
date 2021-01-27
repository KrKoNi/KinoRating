package com.epam.jwd.dto.impl;

import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;
import com.epam.jwd.dto.DataTransferObject;

import java.io.Serializable;

public class UserDTO implements DataTransferObject<User>, Serializable {

    private static final long serialVersionUID = -184268314450344569L;
    private int id;
    private Role role;

    public UserDTO(int id, Role role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public User getObject() {
        return null;
    }
}
