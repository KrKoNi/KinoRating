package com.epam.jwd.domain;

import java.io.Serializable;

/**
 * Genre entity.
 */
public class Genre implements Serializable {

    private int id;
    private String name;
    private static final long serialVersionUID = -1793583206871984521L;


    /**
     * Instantiates a new Genre.
     *
     * @param id   the id
     * @param name the name
     */
    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Instantiates a new Genre.
     */
    public Genre() {

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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
