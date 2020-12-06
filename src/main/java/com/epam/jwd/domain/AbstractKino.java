package com.epam.jwd.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractKino {

    private final int id;
    private final String title;
    private final LocalDate releaseDate;
    private final String imageLink;
    private final String description;
    private final List<Genre> genres = new ArrayList<>();

    public AbstractKino(int id, String title, LocalDate releaseDate, String imageLink, String description) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.imageLink = imageLink;
        this.description = description;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void addGenres(List<Genre> genres) {
        this.genres.addAll(genres);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDescription() {
        return description;
    }
}
