package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Genre;
import com.epam.jwd.domain.Movie;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO implements DataAccessObject<Genre> {

    private static final GenreDAO INSTANCE = new GenreDAO();

    private GenreDAO() {}

    public static GenreDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Genre> readAll() {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<Genre> genres = new ArrayList<>();
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM kinorating.genres")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("genre_id");
                String name = resultSet.getString("genre_name");
                Genre genre = new Genre(id, name);
                genres.add(genre);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return genres;
    }

    @Override
    public List<Genre> readWithOffset(int offset, int num) {
        return null;
    }

    @Override
    public Genre findById(int id) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        Genre genre = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM kinorating.genres where genre_id = ?"
        )) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            String name = resultSet.getString("genre_name");

            genre = new Genre(id, name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return genre;
    }

    @Override
    public void insert(Genre genre) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO kinorating.genres (genre_name) VALUES (?)")) {

            statement.setString(1, genre.getName());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void update(Genre genre) {

    }
}
