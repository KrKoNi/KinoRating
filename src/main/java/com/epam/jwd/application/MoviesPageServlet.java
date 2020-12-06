package com.epam.jwd.application;

import com.epam.jwd.dao.impl.MoviesDAO;
import com.epam.jwd.domain.Movie;

import java.io.*;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "moviesPageServlet", value = "/movies")
public class MoviesPageServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "KinoRating";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println(HelloResource.header());
        out.println("<body>");
        out.println("<h1>" + message + "</h1>");
        List<Movie> movies = MoviesDAO.getInstance().readAll();
        out.println("<div class=\"row\">");
        movies.forEach(movie -> out.println(HelloResource.card(movie.getTitle(), movie.getDescription(), movie.getImageLink())));
        out.println("</div>");
        out.println(HelloResource.footer());
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void destroy() {
    }
}