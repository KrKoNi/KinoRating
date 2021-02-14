package com.epam.jwd.domain;

import java.io.Serializable;

/**
 * Role enum.
 */
public enum Role implements Serializable {

    /**
     * User role.
     */
    USER(1),
    /**
     * Admin role.
     */
    ADMIN(2),
    /**
     * Banned role.
     */
    BANNED(3);

    private static final long serialVersionUID = -1668202005475973442L;

    private final int id;

    Role(int id) {
        this.id = id;
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
     * Gets role by id.
     *
     * @param id the id
     * @return the by id
     */
    public static Role getById(int id) {
        for (Role role : values()) {
            if (role.id == id) return role;
        }
        throw new RuntimeException("Unknown role");
    }
}
