package com.epam.jwd.dto.impl;

import com.epam.jwd.domain.Movie;
import com.epam.jwd.dto.DataTransferObject;

import java.io.Serializable;

public class MovieDTO implements DataTransferObject<Movie>, Serializable {

    private static final long serialVersionUID = -4220813710233927120L;
    private final String title;
    private final String shortDescription;
    private final String imageLink;


    public MovieDTO(String title, String shortDescription, String imageLink) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getImageLink() {
        return imageLink;
    }

    @Override
    public Movie getObject() {
        return null;
    }
}
