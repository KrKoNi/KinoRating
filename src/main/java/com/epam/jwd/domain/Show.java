package com.epam.jwd.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Show {

    private final int id;
    private final String title;
    private final LocalDate releaseDate;
    private final String imageLink;
    private final String shortDescription;
    private final String description;
    private final List<Integer> rates = new ArrayList<>();
    private final List<Genre> genres = new ArrayList<>();

    public Show(int id, String title, LocalDate releaseDate, String imageLink, String shortDescription, String description) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.imageLink = imageLink;
        this.shortDescription = shortDescription;
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

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Integer> getRates() {
        return rates;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
