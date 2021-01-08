package com.epam.jwd.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Show implements Serializable {

    private final String directedBy;
    private final LocalTime duration;


    public Movie(int id, String title, LocalDate releaseDate, String imageLink, String shortDescription, String description, String directedBy, LocalTime duration) {
        super(id, title, releaseDate, imageLink, shortDescription, description);
        this.directedBy = directedBy;
        this.duration = duration;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public LocalTime getDuration() {
        return duration;
    }
}
