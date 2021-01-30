package com.epam.jwd.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Show implements Serializable {

    private static final long serialVersionUID = 3657598425869928229L;
    private int id;
    private String title;
    private String imageLink;
    private String shortDescription;
    private String description;
    private String directedBy;
    private final Map<Integer, Byte> rates = new HashMap<>();
    private final List<Genre> genres = new ArrayList<>();


    public Show(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public Show() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void addGenres(List<Genre> genres) {
        this.genres.addAll(genres);
    }

    public void addRate(int userId, Byte rate) {
        rates.put(userId, rate);
    }

    public void addRate(User user, Byte rate) {
        rates.put(user.getId(), rate);
    }

    public void addRates(Map<Integer, Byte> rates) {
        this.rates.putAll(rates);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public Map<Integer, Byte> getRates() {
        return rates;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }
}
