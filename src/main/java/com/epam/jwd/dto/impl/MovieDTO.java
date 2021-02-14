package com.epam.jwd.dto.impl;

import com.epam.jwd.domain.Movie;
import com.epam.jwd.dto.DataTransferObject;

import java.io.Serializable;

/**
 * Movie dto.
 */
public class MovieDTO implements DataTransferObject<Movie>, Serializable {

    private static final long serialVersionUID = -4220813710233927120L;
    private final String title;
    private final String shortDescription;
    private final String imageLink;


    /**
     * Instantiates a new Movie dto.
     *
     * @param title            the title
     * @param shortDescription the short description
     * @param imageLink        the image link
     */
    public MovieDTO(String title, String shortDescription, String imageLink) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.imageLink = imageLink;
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
     * @return the image link
     */
    public String getImageLink() {
        return imageLink;
    }

    @Override
    public Movie getObject() {
        return null;
    }
}
