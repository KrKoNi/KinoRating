package com.epam.jwd.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class Movie extends Show implements Serializable {

    private LocalTime duration;
    private LocalDate releaseDate;

    private static final long serialVersionUID = -4542598853784377909L;

    public Movie(int id, String title) {
        super(id, title);
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
