package com.epam.jwd.controller.strategy;

import com.epam.jwd.exceptions.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface ControllerAction {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, SQLException;
}
