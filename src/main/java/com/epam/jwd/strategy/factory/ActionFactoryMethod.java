package com.epam.jwd.strategy.factory;

import com.epam.jwd.strategy.Action;
import com.epam.jwd.strategy.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactoryMethod {

    static Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("GET/login", new LoginPageAction());
        actions.put("POST/login", new LoginAction());
        actions.put("GET/index", new HomeAction());
        actions.put("GET/", new HomeAction());
        actions.put("GET/movies", new MoviesPageAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("POST/movies", new MovieEditAction());
        //actions.put("POST/logout", new MoviesPageAction());
    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}
