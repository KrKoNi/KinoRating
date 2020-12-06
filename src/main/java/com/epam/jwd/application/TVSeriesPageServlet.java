package com.epam.jwd.application;

import com.epam.jwd.dao.impl.TVSeriesDAO;
import com.epam.jwd.domain.TVSeries;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "tvseriesPageServlet", value = "/tvseries")
public class TVSeriesPageServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "KinoRating";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println(HelloResource.header());
        out.println("<body>");
        out.println("<h1>" + message + "</h1>");
        List<TVSeries> tvSeriesList = TVSeriesDAO.getInstance().readAll();
        out.println("<div class=\"row\">");
        tvSeriesList.forEach(tvSeries -> out.println(HelloResource.card(tvSeries.getTitle(), tvSeries.getDescription(), tvSeries.getImageLink())));
        out.println("</div>");
        out.println(HelloResource.footer());
        out.println("</body>");
        out.println("</html>");
    }
}
