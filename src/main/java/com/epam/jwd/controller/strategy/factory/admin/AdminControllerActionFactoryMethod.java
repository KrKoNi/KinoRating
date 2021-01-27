package com.epam.jwd.controller.strategy.factory.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.controller.strategy.impl.admin.AdminMainPageControllerAction;
import com.epam.jwd.controller.strategy.impl.admin.AdminMoviesPageControllerAction;
import com.epam.jwd.controller.strategy.impl.home.HomeControllerAction;
import com.epam.jwd.controller.strategy.impl.lang.LanguageChangeControllerAction;
import com.epam.jwd.controller.strategy.impl.login.LoginControllerAction;
import com.epam.jwd.controller.strategy.impl.login.LoginPageControllerAction;
import com.epam.jwd.controller.strategy.impl.login.LogoutControllerAction;
import com.epam.jwd.controller.strategy.impl.movie.MoviePageControllerAction;
import com.epam.jwd.controller.strategy.impl.movie.MoviesPageControllerAction;
import com.epam.jwd.controller.strategy.impl.rate.MovieRateSubmitControllerAction;
import com.epam.jwd.controller.strategy.impl.signup.SignupPageControllerAction;
import com.epam.jwd.controller.strategy.impl.signup.SignupSubmitControllerAction;
import com.epam.jwd.controller.strategy.impl.tv.TVPageControllerAction;
import com.epam.jwd.controller.strategy.impl.tv.TVSeriesPageControllerAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AdminControllerActionFactoryMethod {
    static Map<String, ControllerAction> actions = new HashMap<>();

    static {
        actions.put("GET/index", new AdminMainPageControllerAction());
        actions.put("GET/", new AdminMainPageControllerAction());
        actions.put("GETnull", new AdminMainPageControllerAction());

        actions.put("GET/movies", new AdminMoviesPageControllerAction());
        //actions.put("GET/tv-series", new TVSeriesPageControllerAction());

        //actions.put("GET/movie", new MoviePageControllerAction());
        //actions.put("GET/tv", new TVPageControllerAction());

        //actions.put("POST/movie", new MovieEditControllerAction());
        //actions.put("POST/tv", new MovieEditControllerAction()); //todo tv_edit

        //actions.put("POST/rate", new MovieRateSubmitControllerAction());

        //actions.put("POST/lang", new LanguageChangeControllerAction());



    }

    public static ControllerAction getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}
