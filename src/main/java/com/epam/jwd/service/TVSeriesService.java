package com.epam.jwd.service;

import com.epam.jwd.dao.impl.GenreDAO;
import com.epam.jwd.dao.impl.ShowDAO;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.domain.Genre;
import com.epam.jwd.domain.TVSeries;

import java.sql.SQLException;
import java.util.List;

public class TVSeriesService {
    private static final TVSeriesService INSTANCE = new TVSeriesService();

    private TVSeriesService() {
    }

    public static TVSeriesService getInstance() {
        return INSTANCE;
    }

    public List<TVSeries> readWithOffset(int offset, int num) {
        List<TVSeries> tvSeriesList = TVSeriesDAO.getInstance().readWithOffset(offset, num);

        for (TVSeries tvSeries : tvSeriesList) {
            List<Genre> genres = GenreDAO.getInstance().getGenresByShow(tvSeries);
            tvSeries.addGenres(genres);
        }

        return tvSeriesList;
    }

    public void insert(TVSeries tvSeries) {
        try {
            TVSeriesDAO.getInstance().insert(tvSeries);
            ShowDAO.getInstance().insert(tvSeries);
            GenreDAO.getInstance().addGenresToShow(tvSeries);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
