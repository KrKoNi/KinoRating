package com.epam.jwd.converter.impl;

import com.epam.jwd.converter.Converter;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.dto.impl.MovieDTO;

public class MovieConverter implements Converter<Movie, MovieDTO> {

    @Override
    public Movie toObject(MovieDTO movieDTO) {
        return null;
    }

    @Override
    public MovieDTO toDto(Movie movie) {
        return new MovieDTO(movie.getTitle(), movie.getShortDescription(), movie.getImageLink());
    }
}
