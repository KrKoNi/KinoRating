package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.ProxyConnection;
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
    private static final String SELECT_ROW_COUNT_SQL = "SELECT COUNT(tv_series.id) FROM kinorating.tv_series";
    private static final String INSERT_SQL = "INSERT INTO kinorating.tv_series(id) VALUES ((SELECT LAST_INSERT_ID() from kinorating.abstract_kino LIMIT 1))";
    private static final String SELECT_WITH_OFFSET = "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series limit ?, ?";

    @Override
    public List<TVSeries> readAll(ProxyConnection connection) throws SQLException {
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TVSeries tvSeries = setFieldsFromResultSet(resultSet);
                tvSeriesList.add(tvSeries);
            }
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }

        return tvSeriesList;
    }

    public List<TVSeries> findLike(ProxyConnection connection, String str) throws SQLException {
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LIKE_SQL)) {
            statement.setString(1, "%" + str + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TVSeries tvSeries = setFieldsFromResultSet(resultSet);
                tvSeriesList.add(tvSeries);
            }

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
        return tvSeriesList;
    }

    @Override
    public List<TVSeries> readWithOffset(ProxyConnection connection, int offset, int num) throws SQLException {
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_WITH_OFFSET)) {
            statement.setInt(1, offset);
            statement.setInt(2, num);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TVSeries tvSeries = setFieldsFromResultSet(resultSet);
                tvSeriesList.add(tvSeries);
            }
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
        return tvSeriesList;
    }

    @Override
    public TVSeries findById(ProxyConnection connection, int id) throws SQLException {
        TVSeries tvSeries = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                tvSeries = setFieldsFromResultSet(resultSet);
            }
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }

        return tvSeries;
    }

    @Override
    public void insert(ProxyConnection connection, TVSeries tvSeries) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, tvSeries.getTitle());
            statement.setString(2, tvSeries.getImageLink());

            statement.execute();

        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
    }

    @Override
    public void update(ProxyConnection connection, TVSeries tvSeries) {

    }

    @Override
    public void delete(ProxyConnection connection, TVSeries tvSeries) throws SQLException {
        throw new RuntimeException("Unsupported");
    }

    public int getRowCount(ProxyConnection connection) throws SQLException {
        int rowCount = 0;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ROW_COUNT_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            connection.rollback();
            exception.printStackTrace();
            throw new SQLException(exception);
        }
        return rowCount;
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
