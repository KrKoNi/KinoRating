package com.epam.jwd.domain;

import java.io.Serializable;

public enum Role implements Serializable {

    USER(1),
    ADMIN(2),
    BANNED(3);

    private static final long serialVersionUID = -1668202005475973442L;

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role getById(int id) {
        for (Role role : values()) {
            if (role.id == id) return role;
        }
        throw new RuntimeException("Unknown role");
    }
}
