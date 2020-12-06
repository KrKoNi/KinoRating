package com.epam.jwd.application;

import com.epam.jwd.domain.AbstractKino;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
    public static String header() {
        return "<head>" +
                    "<title>KinoRating</title>" +
                    "<meta charset=\"utf-8\">" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">" +
                    "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                "</head>";
    }
    public static String card(String title, String description, String imageLink) {
        return "<div class=\"col-sm-2\">" +
                    "<div class=\"card\" style=\"width: 18rem;\">" +
                        "<img src=\"" + imageLink + "\" class=\"card-img-top\" width=\"200\" height=\"400\" alt=\"" + title + "\">" +
                        "<div class=\"card-body\"> " +
                            "<h5 class=\"card-title\">" + title + "</h5> " +
                            "<p class=\"card-text\">" + description + "</p> " +
                            "<a href=\"#\" class=\"btn btn-primary\">View</a> " +
                        "</div>" +
                    "</div>" +
                "</div>";
    }
    public static String footer() {
        return "<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>";
    }
}