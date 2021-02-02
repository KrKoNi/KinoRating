package com.epam.jwd.controller.command.factory;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.controller.command.impl.home.HomeControllerAction;
import com.epam.jwd.controller.command.impl.login.LoginControllerAction;
import com.epam.jwd.controller.command.impl.login.LoginPageControllerAction;
import com.epam.jwd.controller.command.impl.login.LogoutControllerAction;
import com.epam.jwd.controller.command.impl.movie.MoviePageControllerAction;
import com.epam.jwd.controller.command.impl.movie.MoviesPageControllerAction;
import com.epam.jwd.controller.command.impl.rate.MovieRateSubmitControllerAction;
import com.epam.jwd.controller.command.impl.signup.SignupPageControllerAction;
import com.epam.jwd.controller.command.impl.signup.SignupSubmitControllerAction;
import com.epam.jwd.controller.command.impl.tv.TVPageControllerAction;
import com.epam.jwd.controller.command.impl.tv.TVSeriesPageControllerAction;
import com.epam.jwd.controller.command.impl.user.UserPageControllerAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactoryMethod {

    static Map<String, ControllerAction> actions = new HashMap<>();

    static {
        actions.put("GET/login", new LoginPageControllerAction());
        actions.put("POST/login", new LoginControllerAction());
        actions.put("POST/logout", new LogoutControllerAction());
        actions.put("GET/signup", new SignupPageControllerAction());
        actions.put("POST/signup", new SignupSubmitControllerAction());

        actions.put("GET/index", new HomeControllerAction());
        actions.put("GET/", new HomeControllerAction());
        actions.put("GETnull", new HomeControllerAction());

        actions.put("GET/movies", new MoviesPageControllerAction());
        actions.put("GET/tv-series", new TVSeriesPageControllerAction());

        actions.put("GET/movie", new MoviePageControllerAction());
        actions.put("GET/tv", new TVPageControllerAction());

        actions.put("GET/user", new UserPageControllerAction());

        actions.put("POST/rate", new MovieRateSubmitControllerAction());
    }

    public static ControllerAction getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}