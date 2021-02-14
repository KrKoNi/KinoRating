package com.epam.jwd.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Show entity.
 */
public abstract class Show implements Serializable {

    private static final long serialVersionUID = 3657598425869928229L;
    private int id;
    private String title;
    private String imageLink;
    private String shortDescription;
    private String description;
    private String directedBy;
    //private final Map<Integer, Byte> rates = new HashMap<>();
    private int currentUserRate;
    private double averageRate;
    private final List<Genre> genres = new ArrayList<>();


    /**
     * Instantiates a new Show.
     *
     * @param id    the id
     * @param title the title
     */
    public Show(int id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Instantiates a new Show.
     */
    public Show() {

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
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Add genre.
     *
     * @param genre the genre
     */
    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    /**
     * Add genres.
     *
     * @param genres the genres
     */
    public void addGenres(List<Genre> genres) {
        this.genres.addAll(genres);
    }

    /*public void addRate(int userId, Byte rate) {
        rates.put(userId, rate);
    }

    public void addRate(User user, Byte rate) {
        rates.put(user.getId(), rate);
    }

    public void addRates(Map<Integer, Byte> rates) {
        this.rates.putAll(rates);
    }*/

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets image link.
     *
     * @return the image link
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets genres.
     *
     * @return the genres
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * Gets short description.
     *
     * @return the short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Gets image link.
     *
     * @param imageLink the image link
     */
/*public Map<Integer, Byte> getRates() {
        return rates;
    }
    */
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    /**
     * Sets short description.
     *
     * @param shortDescription the short description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets directed by.
     *
     * @return the directed by
     */
    public String getDirectedBy() {
        return directedBy;
    }

    /**
     * Sets directed by.
     *
     * @param directedBy the directed by
     */
    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    /**
     * Gets average rate.
     *
     * @return the average rate
     */
    public double getAverageRate() {
        return averageRate;
    }

    /**
     * Sets average rate.
     *
     * @param averageRate the average rate
     */
    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    /**
     * Gets current user rate.
     *
     * @return the current user rate
     */
    public int getCurrentUserRate() {
        return currentUserRate;
    }

    /**
     * Sets current user rate.
     *
     * @param currentUserRate the current user rate
     */
    public void setCurrentUserRate(int currentUserRate) {
        this.currentUserRate = currentUserRate;
    }
}
