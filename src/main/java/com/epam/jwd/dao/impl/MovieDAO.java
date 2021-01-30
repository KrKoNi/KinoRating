package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Movie;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements DataAccessObject<Movie> {

    private static final Logger logger = Logger.getLogger(MovieDAO.class);

    private static final MovieDAO INSTANCE = new MovieDAO();

    private MovieDAO() {
    }

    public static MovieDAO getInstance() {
        return INSTANCE;
    }

    private static final String SELECT_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.movies";
    private static final String SELECT_WITH_OFFSET_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.movies order by id limit ?, ?";
    private static final String SELECT_BY_ID = "SELECT * FROM kinorating.abstract_kino natural join kinorating.movies where id = ?";
    private static final String INSERT_SQL = "INSERT INTO kinorating.movies(id, directed_by, release_date) VALUES ((SELECT LAST_INSERT_ID() from abstract_kino LIMIT 1), ?, ?)";
    private static final String UPDATE_SQL = "UPDATE kinorating.movies SET directed_by = ? where id = ?";
    private static final String SELECT_LIKE_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.movies where title like ?";

    @Override
    public List<Movie> readAll(Connection connection) {
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = setFieldsFromResultSet(resultSet);
                movies.add(movie);
            }
        } catch (SQLException exception) {
            logger.error("Error reading movies", exception);
        }
        return movies;
    }

    @Override
    public List<Movie> readWithOffset(Connection connection, int offset, int num) {
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_WITH_OFFSET_SQL)) {
            statement.setInt(1, offset);
            statement.setInt(2, num);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = setFieldsFromResultSet(resultSet);
                movies.add(movie);
            }
        } catch (SQLException exception) {
            logger.error("Error reading movies with offset", exception);
        }
        return movies;
    }

    @Override
    public Movie findById(Connection connection, int movieId) {
        Movie movie = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, movieId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                movie = setFieldsFromResultSet(resultSet);
            }

        } catch (SQLException exception) {
            logger.error("Error trying to find a movie by id", exception);
        }
        return movie;
    }

    @Override
    public void insert(Connection connection, Movie movie) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, movie.getDirectedBy());
            statement.setDate(2, Date.valueOf(movie.getReleaseDate()));

            statement.execute();

        } catch (SQLException exception) {
            logger.error("Error trying to insert a movie into database", exception);
        }
    }

    @Override
    public void update(Connection connection, Movie movie) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, movie.getDirectedBy());
            statement.setInt(2, movie.getId());

            statement.execute();
        } catch (SQLException exception) {
            logger.error("Error updating movie", exception);
        }
    }

    public List<Movie> findLike(Connection connection, String str) {
        List<Movie> movieList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LIKE_SQL)) {
            statement.setString(1, "%" + str + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = setFieldsFromResultSet(resultSet);
                movieList.add(movie);
            }
        } catch (SQLException throwables) {
            logger.error("Error trying to find a movie by template", throwables);
        }
        return movieList;
    }

    private Movie setFieldsFromResultSet(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setTitle(resultSet.getString("title"));
        movie.setShortDescription(resultSet.getString("short_description"));
        movie.setDescription(resultSet.getString("description"));
        movie.setImageLink(resultSet.getString("image_link"));
        movie.setDirectedBy(resultSet.getString("directed_by"));
        movie.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
        movie.setDuration(resultSet.getTime("duration").toLocalTime());

        return movie;
    }
}
