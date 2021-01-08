package com.epam.jwd.domain;

import java.io.Serializable;

public enum Role implements Serializable {

    USER(1),
    ADMIN(2),
    BANNED(3);

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
