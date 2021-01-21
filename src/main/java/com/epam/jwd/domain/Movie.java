package com.epam.jwd.domain;

import java.io.Serializable;
import java.time.LocalTime;

public class Movie extends Show implements Serializable {


    private LocalTime duration;

    public Movie(int id, String title) {
        super(id, title);
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }
}
