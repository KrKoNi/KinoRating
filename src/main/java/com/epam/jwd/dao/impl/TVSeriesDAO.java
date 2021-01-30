package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.TVSeries;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TVSeriesDAO implements DataAccessObject<TVSeries> {

    private static final Logger logger = Logger.getLogger(TVSeriesDAO.class);

    private static final TVSeriesDAO INSTANCE = new TVSeriesDAO();

    private TVSeriesDAO() {}

    public static TVSeriesDAO getInstance() {
        return INSTANCE;
    }

    private static final String SELECT_ALL_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series";
    private static final String SELECT_LIKE_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series where title like ?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series WHERE id = ?";
    private static final String INSERT_SQL = "INSERT INTO kinorating.tv_series(id) VALUES ((SELECT LAST_INSERT_ID() from kinorating.abstract_kino LIMIT 1))";
    private static final String SELECT_WITH_OFFSET = "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series limit ?, ?";

    @Override
    public List<TVSeries> readAll(Connection connection) {
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TVSeries tvSeries = setFieldsFromResultSet(resultSet);
                tvSeriesList.add(tvSeries);
            }
        } catch (SQLException throwables) {
            logger.error("Error reading TV-Series from database", throwables);
        }

        return tvSeriesList;
    }

    public List<TVSeries> findLike(Connection connection, String str) {
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LIKE_SQL)) {
            statement.setString(1, "%" + str + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TVSeries tvSeries = setFieldsFromResultSet(resultSet);
                tvSeriesList.add(tvSeries);
            }

        } catch (SQLException throwables) {
            logger.error("Error trying to find TV-Series by string template", throwables);
        }
        return tvSeriesList;
    }

    @Override
    public List<TVSeries> readWithOffset(Connection connection, int offset, int num) {
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_WITH_OFFSET)) {
            statement.setInt(1, offset);
            statement.setInt(2, num);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TVSeries tvSeries = setFieldsFromResultSet(resultSet);
                tvSeriesList.add(tvSeries);
            }
        } catch (SQLException throwables) {
            logger.error("Error reading TV-Series from database", throwables);
        }
        return tvSeriesList;
    }

    @Override
    public TVSeries findById(Connection connection, int id) {
        TVSeries tvSeries = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                tvSeries = setFieldsFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            logger.error("Error trying to find TV-Series by ID", throwables);
        }

        return tvSeries;
    }

    @Override
    public void insert(Connection connection, TVSeries tvSeries) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, tvSeries.getTitle());
            statement.setString(2, tvSeries.getImageLink());

            statement.execute();

        } catch (SQLException throwables) {
            logger.error("Error inserting TV-Series into database", throwables);
        }
    }

    @Override
    public void update(Connection connection, TVSeries tvSeries) {

    }

    private TVSeries setFieldsFromResultSet(ResultSet resultSet) throws SQLException {
        TVSeries tvSeries = new TVSeries();
        tvSeries.setId(resultSet.getInt("id"));
        tvSeries.setTitle(resultSet.getString("title"));
        tvSeries.setDescription(resultSet.getString("description"));
        tvSeries.setShortDescription(resultSet.getString("short_description"));
        tvSeries.setImageLink(resultSet.getString("image_link"));

        return tvSeries;
    }
}
