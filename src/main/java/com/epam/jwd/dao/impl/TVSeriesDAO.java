package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Movie;
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

    private static final String SELECT_ALL_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series";
    private static final String SELECT_LIKE_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series where title like ?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series WHERE id = ?";
    private static final String INSERT_SQL = "INSERT INTO kinorating.tv_series(id) VALUES ((SELECT LAST_INSERT_ID() from kinorating.abstract_kino LIMIT 1))";
    private static final String SELECT_WITH_OFFSET = "SELECT * FROM kinorating.abstract_kino natural join kinorating.tv_series limit ?, ?";

    private int id;
    private String title;
    private String imageLink;
    private String shortDescription;
    private String description;

    private TVSeries tvSeries;

    private void setParams(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        title = resultSet.getString("title");
        imageLink = resultSet.getString("image_link");
        shortDescription = resultSet.getString("short_description");
        description = resultSet.getString("description");
    }

    @Override
    public List<TVSeries> readAll() {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                setParams(resultSet);
                initTvSeries();
                tvSeriesList.add(tvSeries);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }

        return tvSeriesList;
    }

    public List<TVSeries> findLike(String str) {
        List<TVSeries> tvSeriesList = new ArrayList<>();
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_LIKE_SQL)) {
            statement.setString(1, "%" + str + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                setParams(resultSet);
                initTvSeries();
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
    public List<TVSeries> readWithOffset(int offset, int num) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<TVSeries> tvSeriesList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_WITH_OFFSET)) {
            statement.setInt(1, offset);
            statement.setInt(2, num);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                setParams(resultSet);
                initTvSeries();
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
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                setParams(resultSet);
                initTvSeries();
            }
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
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, tvSeries.getTitle());
            statement.setString(2, tvSeries.getImageLink());

            statement.execute();

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

    private void initTvSeries() {
        tvSeries = new TVSeries(id, title);
        tvSeries.setDescription(description);
        tvSeries.setShortDescription(shortDescription);
        tvSeries.setImageLink(imageLink);
    }
}
