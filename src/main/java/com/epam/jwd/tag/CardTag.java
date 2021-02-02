package com.epam.jwd.tag;

import com.epam.jwd.domain.Movie;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.Show;
import com.epam.jwd.domain.TVSeries;
import com.epam.jwd.dto.impl.UserDTO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class CardTag extends TagSupport {



    private Show show;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();


        UserDTO userDTO = (UserDTO) pageContext.getSession().getAttribute("userDTO");

        String type;

        if (show instanceof Movie) {
            type = "movie";
        } else if (show instanceof TVSeries) {
            type = "tv";
        } else return SKIP_BODY;

        int userRate;

        if (userDTO != null && show.getRates().containsKey(userDTO.getId())) {
            userRate = show.getRates().get(userDTO.getId());
        } else userRate = 0;
        try {
            out.print("<div class='col mb-4'>");
            out.print("<div class='card bg-dark'>");
            out.print(String.format("<a href='%s/app/%s?id=%d'>", pageContext.getServletContext().getContextPath(), type, show.getId()));
            out.print(String.format("<img onloadstart='setActive(%d, %d)' height='300px' src=%s class='card-img-top' alt=%s>", show.getId(), userRate, show.getImageLink(), show.getTitle()));
            out.print("</a>");
            out.print("<div class='card-body'>");
            out.print(String.format("<h5 class='card-title'>%s</h5>", show.getTitle()));
            out.print(String.format("<p class='card-text'>%s</p>", show.getShortDescription()));
            out.print(String.format("<div class='star-rating' id='%d' style='direction: rtl'>", show.getId()));
            for (int i = 10; i >= 1; i--) {
                out.print(String.format("<span class='%d' onmouseout='setActive(%d, %d)' onmouseover='setActive(%d, %d)' onclick='sendRate(%d, %d)'><i class='fa fa-star'></i></span>", i, show.getId(), userRate, show.getId(), i, show.getId(), i));
            }
            out.print("</div>");
            if(userRate != 0) {
                out.print(String.format("<span><fmt:message key='msg.your-rate'/>: %d </span>", userRate));
                out.print(String.format("<button onclick='removeRate(%d)' type='button' class='btn btn-dark'><fmt:message key='msg.remove-rate'/></button>", show.getId()));
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

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
