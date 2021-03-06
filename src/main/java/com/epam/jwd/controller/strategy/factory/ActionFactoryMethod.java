package com.epam.jwd.controller.strategy.factory;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.controller.strategy.impl.home.HomeControllerAction;
import com.epam.jwd.controller.strategy.impl.login.LoginControllerAction;
import com.epam.jwd.controller.strategy.impl.login.LoginPageControllerAction;
import com.epam.jwd.controller.strategy.impl.login.LogoutControllerAction;
import com.epam.jwd.controller.strategy.impl.movie.MoviePageControllerAction;
import com.epam.jwd.controller.strategy.impl.movie.MoviesPageControllerAction;
import com.epam.jwd.controller.strategy.impl.rate.MovieRateSubmitControllerAction;
import com.epam.jwd.controller.strategy.impl.search.SearchShowsControllerAction;
import com.epam.jwd.controller.strategy.impl.signup.SignupPageControllerAction;
import com.epam.jwd.controller.strategy.impl.signup.SignupSubmitControllerAction;
import com.epam.jwd.controller.strategy.impl.tv.TVPageControllerAction;
import com.epam.jwd.controller.strategy.impl.tv.TVSeriesPageControllerAction;
import com.epam.jwd.controller.strategy.impl.user.EditUserPageControllerAction;
import com.epam.jwd.controller.strategy.impl.user.EditUserSubmitControllerAction;
import com.epam.jwd.controller.strategy.impl.user.UserPageControllerAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Action factory method.
 */
public class ActionFactoryMethod {

    /**
     * The Actions.
     */
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

        actions.put("GET/search_result", new SearchShowsControllerAction());

        actions.put("GET/movie", new MoviePageControllerAction());
        actions.put("GET/tv", new TVPageControllerAction());

        actions.put("GET/user", new UserPageControllerAction());
        actions.put("GET/edit_user", new EditUserPageControllerAction());
        actions.put("POST/update_user", new EditUserSubmitControllerAction());

        actions.put("POST/rate", new MovieRateSubmitControllerAction());
    }

    /**
     * Gets action.
     *
     * @param request the request
     * @return the action
     */
    public static ControllerAction getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}
