package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.ProxyConnection;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.movies where id = ?";
    private static final String SELECT_ROW_COUNT_SQL = "SELECT COUNT(movies.id) FROM kinorating.movies";
    private static final String INSERT_SQL = "INSERT INTO kinorating.movies(id, directed_by, release_date) VALUES ((SELECT LAST_INSERT_ID() from abstract_kino LIMIT 1), ?, ?)";
    private static final String UPDATE_SQL = "UPDATE kinorating.movies SET directed_by = ? where id = ?";
    private static final String SELECT_LIKE_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.movies where title like ?";
    private static final String CONTAINS_ID_SQL = "SELECT COUNT(movies.id) from kinorating.movies where movies.id = ?";

    @Override
    public List<Movie> readAll(ProxyConnection connection) throws DaoException {
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = setFieldsFromResultSet(resultSet);
                movies.add(movie);
            }
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new DaoException(exception);
        }
        return movies;
    }

    @Override
    public List<Movie> readWithOffset(ProxyConnection connection, int offset, int num) throws DaoException {
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
            connection.rollback();
            exception.printStackTrace();
            throw new DaoException(exception);
        }
        return movies;
    }

    @Override
    public Movie findById(ProxyConnection connection, int movieId) throws DaoException {
        Movie movie = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setInt(1, movieId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                movie = setFieldsFromResultSet(resultSet);
            }

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new DaoException(exception);
        }
        return movie;
    }

    @Override
    public void insert(ProxyConnection connection, Movie movie) throws DaoException {

        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, movie.getDirectedBy());
            statement.setDate(2, Date.valueOf(movie.getReleaseDate()));

            statement.executeUpdate();

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new DaoException(exception);
        }
    }

    public int insertReturningId(ProxyConnection connection, Movie movie) throws DaoException {
        int id;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, movie.getDirectedBy());
            statement.setDate(2, Date.valueOf(movie.getReleaseDate()));

            statement.execute();

            id = statement.getGeneratedKeys().getInt("id");

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new DaoException(exception);
        }
        return id;
    }

    @Override
    public void update(ProxyConnection connection, Movie movie) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, movie.getDirectedBy());
            statement.setInt(2, movie.getId());

            statement.execute();
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new DaoException(exception);
        }
    }

    @Override
    public void delete(ProxyConnection connection, Movie movie) {
        throw new RuntimeException("Unsupported");
    }

    public List<Movie> findLike(ProxyConnection connection, String str) throws DaoException {
        List<Movie> movieList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LIKE_SQL)) {
            statement.setString(1, "%" + str + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = setFieldsFromResultSet(resultSet);
                movieList.add(movie);
            }
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new DaoException(exception);
        }
        return movieList;
    }

    public int getRowCount(ProxyConnection connection) throws DaoException {
        int rowCount = 0;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ROW_COUNT_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new DaoException(exception);
        }
        return rowCount;
    }

    public boolean isMovie(ProxyConnection connection, int id) throws DaoException {
        boolean isMovie = false;
        try (PreparedStatement statement = connection.prepareStatement(CONTAINS_ID_SQL)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isMovie = resultSet.getInt(1) == 1;
            }
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new DaoException(exception);
        }
        return isMovie;
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
