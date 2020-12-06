package com.epam.jwd.domain;

public enum Genre {
    UNKNOWN(0, "unknown");
    private final int id;
    private final String name;

    Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static Genre getById(Long id) {
        for(Genre genre : values()) {
            if(genre.id == id) return genre;
        }
        return UNKNOWN;
    }


}
