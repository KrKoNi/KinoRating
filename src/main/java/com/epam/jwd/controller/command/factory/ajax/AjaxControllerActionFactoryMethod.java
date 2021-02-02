package com.epam.jwd.controller.command.factory.ajax;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.controller.command.impl.ajax.AjaxRateControllerAction;
import com.epam.jwd.controller.command.impl.ajax.AjaxSearchControllerAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AjaxControllerActionFactoryMethod {
    static Map<String, ControllerAction> actions = new HashMap<>();

    static {
        actions.put("POST/rate", new AjaxRateControllerAction());
        actions.put("GET/rate", new AjaxRateControllerAction());

        actions.put("GET/search", new AjaxSearchControllerAction());
    }

    public static ControllerAction getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}