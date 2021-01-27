package com.epam.jwd.domain;

import java.io.Serializable;

public class Genre implements Serializable {

    private int id;
    private String name;
    private static final long serialVersionUID = -1793583206871984521L;


    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
