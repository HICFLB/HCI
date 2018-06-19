package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus1 on 2018/3/20.
 */
@Controller
public class LogoutController {

    @RequestMapping(value = "userLogout")
    public String userLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.removeAttribute("user");
        return "index";
    }

    @RequestMapping(value = "venueLogout")
    public String venueLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.removeAttribute("venue");
        return "views/Venue/apply";
    }
}
