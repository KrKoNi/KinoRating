package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieDAO implements DataAccessObject<Movie> {

    private int id;
    private String title;
    private LocalDate releaseDate;
    private String imageLink;
    private String shortDescription;
    private String description;
    private String directedBy;
    private LocalTime duration;
    private Movie movie;

    private static final MovieDAO INSTANCE = new MovieDAO();

    private MovieDAO() {
    }

    public static MovieDAO getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Movie> readAll() {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM kinorating.abstract_kino natural join kinorating.movies"
        )) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                setParams(resultSet);

                movie = new Movie(id, title, releaseDate, imageLink, shortDescription, description, directedBy, duration);
                ShowDAO.getInstance().initShowRates(movie);
                movies.add(movie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return movies;
    }

    @Override
    public List<Movie> readWithOffset(int offset, int num) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<Movie> movies = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * " +
                        "FROM kinorating.abstract_kino natural join kinorating.movies " +
                        "order by id " +
                        "limit ?, ?"
        )) {
            statement.setInt(1, offset);
            statement.setInt(2, num);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                setParams(resultSet);
                movie = new Movie(id, title, releaseDate, imageLink, shortDescription, description, directedBy, duration);
                ShowDAO.getInstance().initShowRates(movie);
                movies.add(movie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return movies;
    }

    private void setParams(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        title = resultSet.getString("title");
        releaseDate = resultSet.getDate("release_date").toLocalDate();
        imageLink = resultSet.getString("image_link");
        shortDescription = resultSet.getString("short_description");
        description = resultSet.getString("description");
        directedBy = resultSet.getString("directed_by");
        duration = resultSet.getTime("duration").toLocalTime();
    }

    @Override
    public Movie findById(int movie_id) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        Movie movie = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM kinorating.abstract_kino natural join kinorating.movies where id = ?"
        )) {
            statement.setInt(1, movie_id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                setParams(resultSet);
            }

            movie = new Movie(movie_id, title, releaseDate, imageLink, shortDescription, description, directedBy, duration);
            ShowDAO.getInstance().initShowRates(movie);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return movie;
    }

    @Override
    public void insert(Movie movie) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (
                PreparedStatement statement = connection.
                        prepareStatement(
                                "INSERT INTO kinorating.abstract_kino (title, release_date, image_link) VALUES (?, ?, ?)"
                        );
                PreparedStatement statement1 = connection.
                        prepareStatement(
                                "INSERT INTO kinorating.movies(id, directed_by) VALUES ((SELECT LAST_INSERT_ID() from abstract_kino), ?)"
                        )
        ) {
            connection.setAutoCommit(false);

            statement.setString(1, movie.getTitle());
            statement.setDate(2, Date.valueOf(movie.getReleaseDate()));
            statement.setString(3, movie.getImageLink());

            statement1.setString(1, movie.getDirectedBy());

            statement.execute();
            statement1.execute();

            connection.commit();
        } catch (SQLException throwables) {
            connection.rollback();
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void update(Movie movie) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE kinorating.abstract_kino SET " +
                        "title = ?, " +
                        "release_date = ?, " +
                        "image_link = ? " +
                        "where id = ?");
             PreparedStatement statement1 = connection.prepareStatement(
                "UPDATE kinorating.movies SET " +
                        "directed_by = ? " +
                        "where id = ?")
        ) {
            connection.setAutoCommit(false);

            statement.setString(1, movie.getTitle());
            statement.setDate(2, Date.valueOf(movie.getReleaseDate()));
            statement.setString(3, movie.getImageLink());
            statement.setInt(4, movie.getId());

            statement1.setString(1, movie.getDirectedBy());
            statement1.setInt(2, movie.getId());

            statement.execute();
            statement1.execute();

            connection.commit();
        } catch (SQLException throwables) {
            connection.rollback();
            throwables.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }

    }

}
