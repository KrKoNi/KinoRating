package com.epam.jwd.tag;

import com.epam.jwd.converter.impl.UserConverter;
import com.epam.jwd.domain.Genre;
import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.dto.impl.UserDTO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Card tag.
 */
public class CardTag extends TagSupport {

    private Show show;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        final Cookie[] cookies = request.getCookies();
        String lang = "en";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("lang".equals(cookie.getName())) {
                    lang = cookie.getValue();
                }
            }
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle("text_" + lang);

        UserDTO userDTO = (UserDTO) pageContext.getSession().getAttribute("userDTO");

        String type;

        if (show instanceof Movie) {
            type = "movie";
        } else if (show instanceof TVSeries) {
            type = "tv";
        } else return SKIP_BODY;

        int userRate;

        if (userDTO != null) {
            Map<Integer, Byte> userRates = UserConverter.getInstance().toObject(userDTO).getRates();
            userRate = userRates.containsKey(show.getId()) ? userRates.get(show.getId()) : 0;
            show.setCurrentUserRate(userRate);
        } else userRate = 0;

        try {
            out.print("<div class='col mb-4'>");
            out.print("<div class='card bg-dark'>");
            out.print(String.format("<a href='%s/app/%s?id=%d'>", pageContext.getServletContext().getContextPath(), type, show.getId()));
            out.print(String.format("<img onloadstart='setActive(%d, %d)' height='300px' src=%s class='card-img-top' alt=%s>", show.getId(), userRate, show.getImageLink(), show.getTitle()));
            out.print("</a>");
            out.print("<div class='card-body'>");
            out.print(String.format("<h5 class='card-title'>%s</h5>", show.getTitle()));
            String genres = show.getGenres().stream()
                    .map(Genre::getName)
                    .collect(Collectors.joining(", "));
            out.print(String.format("<p class='card-text'>%s</p>", genres));
            out.print(String.format("<p class='card-text'>%s: %.2f</p>", resourceBundle.getString("msg.average-rate"), show.getAverageRate()));
            if(userDTO != null) {
                out.print(String.format("<div class='star-rating' id='%d' style='direction: rtl'>", show.getId()));
                for (int i = 10; i >= 1; i--) {
                    out.print(String.format("<span class='%d' onmouseout='setActive(%d, %d)' onmouseover='setActive(%d, %d)' onclick='sendRate(%d, %d)'><i class='fa fa-star'></i></span>", i, show.getId(), userRate, show.getId(), i, show.getId(), i));
                }
                out.print("</div>");
                if(userRate != 0) {
                    out.print(String.format("<span>%s: %d</span>", resourceBundle.getString("msg.your-rate"), userRate));
                    out.print(String.format("<button onclick='removeRate(%d)' type='button' class='btn btn-dark'>%s</button>", show.getId(), resourceBundle.getString("msg.remove-rate") ));
                }
            }
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }

    /**
     * Gets show.
     *
     * @return the show
     */
    public Show getShow() {
        return show;
    }

    /**
     * Sets show.
     *
     * @param show the show
     */
    public void setShow(Show show) {
        this.show = show;
    }
}
