package com.epam.jwd.controller.strategy;

import com.epam.jwd.exceptions.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * The interface Controller action.
 */
public interface ControllerAction {
    /**
     * Interface for strategy pattern, impl classes override method doing some controller job,
     * returns string contains name of corresponding jsp.
     *
     * @param request  http request
     * @param response http response
     * @return string equals to corresponding jsp
     * @throws ActionException action exception
     * @throws SQLException    sql exception
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, SQLException;
}
