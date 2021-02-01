package com.epam.jwd.controller.command.impl.ajax;

import com.epam.jwd.controller.command.ControllerAction;
import com.epam.jwd.dto.impl.UserDTO;
import com.epam.jwd.service.ShowService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AjaxRateControllerAction implements ControllerAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDTO userDTO = (UserDTO) request.getSession(false).getAttribute("userDTO");
        int showId = Integer.parseInt(request.getParameter("id"));

        if(request.getParameter("rate") == null) {
            ShowService.removeRate(showId, userDTO.getId());
            return "ajax";
        }
        byte rate = Byte.parseByte(request.getParameter("rate"));
        ShowService.addRate(showId, userDTO.getId(), rate);
        return "ajax";
    }
}
