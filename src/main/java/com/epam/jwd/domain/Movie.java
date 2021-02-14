package com.epam.jwd.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Movie entity.
 */
public class Movie extends Show implements Serializable {

    private LocalTime duration;
    private LocalDate releaseDate;

    private static final long serialVersionUID = -4542598853784377909L;

    /**
     * Instantiates a new Movie.
     *
     * @param id    the id
     * @param title the title
     */
    public Movie(int id, String title) {
        super(id, title);
    }

    /**
     * Instantiates a new Movie.
     */
    public Movie() {
        super();
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public LocalTime getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    /**
     * Gets release date.
     *
     * @return the release date
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets release date.
     *
     * @param releaseDate the release date
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
