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
        } catch (SQLException exception) {
            logger.error("Genres init error", exception);
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

    public void addGenresToShow(Show show) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<Genre> genres = show.getGenres();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO kinorating.kino_genres (kino_id, genre_id) VALUES (?, ?)"
        )) {
            connection.setAutoCommit(false);

            for (Genre genre : genres) {
                statement.setInt(1, show.getId());
                statement.setInt(2, genre.getId());
                statement.addBatch();
            }

            statement.executeBatch();

            connection.commit();

        } catch (SQLException exception) {
            connection.rollback();
            logger.error("Error adding genres to a show", exception);
        }
        finally {
            connection.setAutoCommit(true);
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public List<Genre> getGenresByShow(Show show) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<Genre> genres = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM kinorating.kino_genres where kino_id = ?"
        )) {
            statement.setInt(1, show.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                genres.add(GenreService.findById(resultSet.getInt("genre_id")));
            }

        } catch (SQLException throwables) {
            logger.error("Error finding genres of the show", throwables);
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return genres;
    }

    @Override
    public void update(Genre genre) {

    }
}
