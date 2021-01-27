package com.epam.jwd.service;

import com.epam.jwd.dao.impl.MovieDAO;
import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.dao.impl.UserDAO;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.TVSeries;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ShowService {
    private static final Logger logger = Logger.getLogger(ShowService.class);

    private static final ShowService INSTANCE = new ShowService();

    private ShowService() {
    }

    public static ShowService getInstance() {
        return INSTANCE;
    }

    public List<Show> findLike(String str) {
        List<Movie> movies = MovieDAO.getInstance().findLike(str);
        List<TVSeries> tvSeriesList = TVSeriesDAO.getInstance().findLike(str);

        List<Show> showList = new ArrayList<>();
        showList.addAll(movies);
        showList.addAll(tvSeriesList);

        return showList;
    }

}
