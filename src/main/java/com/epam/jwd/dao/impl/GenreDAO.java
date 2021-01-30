package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Genre;
import com.epam.jwd.domain.Show;
import com.epam.jwd.service.GenreService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO implements DataAccessObject<Genre> {

    private static final GenreDAO INSTANCE = new GenreDAO();

    private GenreDAO() {}

    public static GenreDAO getInstance() {
        return INSTANCE;
    }

    private static final Logger logger = Logger.getLogger(GenreDAO.class);

    @Override
    public List<Genre> readAll(Connection connection) throws SQLException {
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
        } catch (SQLException exception) {
            logger.error("Genres init error", exception);
            throw new SQLException(exception);
        }
        return genres;
    }

    @Override
    public List<Genre> readWithOffset(Connection connection, int offset, int num) {
        throw new RuntimeException("Unavailable operation");
    }

    @Override
    public Genre findById(Connection connection, int id) throws SQLException {
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
            throw new SQLException(throwables);
        }
        return genre;
    }

    @Override
    public void insert(Connection connection, Genre genre) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO kinorating.genres (genre_name) VALUES (?)")) {

            statement.setString(1, genre.getName());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException(throwables);
        }
    }



    @Override
    public void update(Connection connection, Genre genre) {
        throw new RuntimeException("Unavailable operation");
    }
}
