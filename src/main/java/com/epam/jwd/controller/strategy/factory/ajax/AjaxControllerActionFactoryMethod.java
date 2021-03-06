package com.epam.jwd.controller.strategy.factory.ajax;

import com.epam.jwd.controller.strategy.ControllerAction;
import com.epam.jwd.controller.strategy.impl.ajax.AjaxRateControllerAction;
import com.epam.jwd.controller.strategy.impl.ajax.AjaxSearchControllerAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Ajax controller action factory method.
 */
public class AjaxControllerActionFactoryMethod {
    /**
     * The Actions.
     */
    static Map<String, ControllerAction> actions = new HashMap<>();

    static {
        actions.put("POST/rate", new AjaxRateControllerAction());
        actions.put("GET/rate", new AjaxRateControllerAction());

        actions.put("GET/search", new AjaxSearchControllerAction());
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
