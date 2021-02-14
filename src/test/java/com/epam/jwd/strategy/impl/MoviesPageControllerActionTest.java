package com.epam.jwd.strategy.impl;

import com.epam.jwd.controller.strategy.impl.movie.MoviesPageControllerAction;
import com.epam.jwd.domain.Role;
import com.epam.jwd.dto.impl.UserDTO;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



class MoviesPageControllerActionTest {

    @Test
    void execute() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userDTO")).thenReturn(new UserDTO(1, Role.USER));
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("page")).thenReturn("3");

        String result = new MoviesPageControllerAction().execute(request, response);
        assertEquals("movies", result);

    }
}