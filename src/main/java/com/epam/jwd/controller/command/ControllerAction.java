package com.epam.jwd.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerAction {
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
