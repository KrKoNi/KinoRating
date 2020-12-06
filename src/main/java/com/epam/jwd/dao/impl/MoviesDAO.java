package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Movie;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MoviesDAO implements DataAccessObject<Movie> {

    private static final MoviesDAO INSTANCE = new MoviesDAO();

    private MoviesDAO() {}

    public static MoviesDAO getInstance() {
        return INSTANCE;
    }

    public List<Movie> readAll() {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, title, release_date, image_link, description FROM kinorating.kino WHERE type = 1 LIMIT 30")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                LocalDate releaseDate = resultSet.getDate(3).toLocalDate();
                String imageLink = resultSet.getString(4);
                String description = resultSet.getString(5);
                Movie movie = new Movie(id, title, releaseDate, imageLink, description);
                movies.add(movie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return movies;
    }

    @Override
    public void insert(Movie movie) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO kinorating.movies(title, release_date, image_link) valuse(?, ?, ?)")) {
            statement.setString(1, movie.getTitle());
            statement.setDate(2, Date.valueOf(movie.getReleaseDate()));
            statement.setString(3, movie.getImageLink());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void update(Movie movie) {

    }
}
