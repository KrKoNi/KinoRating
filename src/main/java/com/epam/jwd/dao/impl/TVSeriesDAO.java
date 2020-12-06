package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.TVSeries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TVSeriesDAO implements DataAccessObject<TVSeries> {

    private static final TVSeriesDAO INSTANCE = new TVSeriesDAO();

    private TVSeriesDAO() {}

    public static TVSeriesDAO getInstance() {
        return INSTANCE;
    }

    public List<TVSeries> readAll() {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, title, release_date, image_link, description FROM kinorating.kino WHERE type = 2 LIMIT 30")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                LocalDate releaseDate = resultSet.getDate(3).toLocalDate();
                String imageLink = resultSet.getString(4);
                String description = resultSet.getString(5);
                TVSeries tvSeries = new TVSeries(id, title, releaseDate, imageLink, description);

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
    public void insert(TVSeries tvSeries) {

    }

    @Override
    public void update(TVSeries tvSeries) {

    }
}
