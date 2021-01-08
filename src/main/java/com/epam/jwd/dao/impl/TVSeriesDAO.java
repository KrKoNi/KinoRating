package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.TVSeries;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TVSeriesDAO implements DataAccessObject<TVSeries> {

    private static final TVSeriesDAO INSTANCE = new TVSeriesDAO();

    private TVSeriesDAO() {}

    public static TVSeriesDAO getInstance() {
        return INSTANCE;
    }

    private int id;
    private String title;
    private LocalDate releaseDate;
    private String imageLink;
    private String shortDescription;
    private String description;

    private void setParams(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        title = resultSet.getString("title");
        releaseDate = resultSet.getDate("release_date").toLocalDate();
        imageLink = resultSet.getString("image_link");
        shortDescription = resultSet.getString("short_description");
        description = resultSet.getString("description");
    }

    @Override
    public List<TVSeries> readAll() {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series"
        )) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                setParams(resultSet);
                TVSeries tvSeries = new TVSeries(id, title, releaseDate, imageLink, shortDescription, description);

                tvSeriesList.add(tvSeries);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }

        return tvSeriesList;
    }

    @Override
    public TVSeries findById(int id) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        TVSeries tvSeries = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series WHERE kinorating.tv_series.id = (?)"
        )) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            setParams(resultSet);

            tvSeries = new TVSeries(id, title, releaseDate, imageLink, shortDescription, description);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }

        return tvSeries;
    }

    @Override
    public void insert(TVSeries tvSeries) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO kinorating.abstract_kino (title, release_date, image_link) VALUES (?, ?, ?)");
                PreparedStatement statement2 = connection
                        .prepareStatement("INSERT INTO kinorating.tv_series(id) VALUES ((SELECT LAST_INSERT_ID() from kinorating.abstract_kino LIMIT 1))")
        ) {
            statement.setString(1, tvSeries.getTitle());
            statement.setDate(2, Date.valueOf(tvSeries.getReleaseDate()));
            statement.setString(3, tvSeries.getImageLink());

            statement.execute();
            statement2.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void update(TVSeries tvSeries) {

    }
}
