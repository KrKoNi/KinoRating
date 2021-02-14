package com.epam.jwd.dto.impl;

import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;
import com.epam.jwd.dto.DataTransferObject;

import java.io.Serializable;

/**
 * User dto.
 */
public class UserDTO implements DataTransferObject<User>, Serializable {

    private static final long serialVersionUID = -184268314450344569L;
    private int id;
    private Role role;

    /**
     * Instantiates a new User dto.
     *
     * @param id   the id
     * @param role the role
     */
    public UserDTO(int id, Role role) {
        this.id = id;
        this.role = role;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public User getObject() {
        return null;
    }
}
