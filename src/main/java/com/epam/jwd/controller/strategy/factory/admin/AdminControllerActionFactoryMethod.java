package com.epam.jwd.controller.strategy.factory.admin;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.controller.strategy.impl.admin.*;
import com.epam.jwd.controller.strategy.impl.home.HomeControllerAction;
import com.epam.jwd.controller.strategy.impl.movie.MoviePageControllerAction;
import com.epam.jwd.controller.strategy.impl.movie.MoviesPageControllerAction;
import com.epam.jwd.controller.strategy.impl.tv.TVPageControllerAction;
import com.epam.jwd.controller.strategy.impl.tv.TVSeriesPageControllerAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Admin controller action factory method.
 */
public class AdminControllerActionFactoryMethod {
    /**
     * The Actions.
     */
    static Map<String, ControllerAction> actions = new HashMap<>();

    static {
        actions.put("GET/index", new HomeControllerAction());
        actions.put("GET/", new HomeControllerAction());
        actions.put("GETnull", new HomeControllerAction());

        actions.put("GET/users", new UsersPageControllerAction());
        actions.put("GET/movies", new MoviesPageControllerAction());
        actions.put("GET/tv-series", new TVSeriesPageControllerAction());

        actions.put("GET/movie", new MoviePageControllerAction());
        actions.put("GET/tv", new TVPageControllerAction());

        actions.put("POST/update_movie", new UpdateMovieControllerAction());
        actions.put("POST/update_tv", new UpdateTVControllerAction());

        actions.put("POST/delete_show", new RemoveShowControllerAction());

        actions.put("GET/create_movie", new CreateMoviePageControllerAction());
        actions.put("POST/create_movie", new CreateMovieSubmitControllerAction());

        actions.put("GET/create_tv", new CreateTVPageControllerAction());
        actions.put("POST/create_tv", new CreateTVSubmitController());

        //actions.put("POST/make_user", );
        //actions.put("POST/make_admin", );
        actions.put("POST/delete_user", new RemoveUserControllerAction());
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
