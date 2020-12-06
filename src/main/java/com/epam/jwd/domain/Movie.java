package com.epam.jwd.domain;

import java.time.LocalDate;

public class Movie extends AbstractKino {

    public Movie(int id, String title, LocalDate releaseDate, String imageLink, String description) {
        super(id, title, releaseDate, imageLink, description);
    }

}
