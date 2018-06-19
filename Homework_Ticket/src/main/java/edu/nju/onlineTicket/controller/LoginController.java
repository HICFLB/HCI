package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.User;
import edu.nju.onlineTicket.model.Venue;
import edu.nju.onlineTicket.service.UserService;
import edu.nju.onlineTicket.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus1 on 2018/3/8.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private VenueService venueService;

    @RequestMapping(value = "/Manager")
    public String Venue() throws Exception {
        return "/views/Manager/managerLogin";
    }

    @RequestMapping(value = "login")
    public void Login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        response.setCharacterEncoding("UTF-8");
        User user = userService.findUser(email);
        if(user!=null){
            if(password.equals(user.getPassword())){
                if(user.getIsLogOff()==1) {
                    response.getWriter().print("IsLogOff");
                }else{
                    request.getSession().setAttribute("user",user);
                    response.getWriter().print("Success");
                }
            } else {
                response.getWriter().print("WrongPassword");
            }
        }else {
            response.getWriter().print("NoRegister");
        }
    }

    @RequestMapping(value = "venueLogin")
    public void venueLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String venueID = request.getParameter("venueID");
        String password = request.getParameter("password");
        response.setCharacterEncoding("UTF-8");
        Venue venue = venueService.findVenue(venueID);
        if(venue!=null){
            if(password.equals(venue.getPassword())){
                if(venue.getIsChecked()==0) {
                    response.getWriter().print("NotChecked");
                }else if(venue.getIsPassed()==1){
                    request.getSession().setAttribute("venue",venue);
                    response.getWriter().print("Success");
                }else if(venue.getIsPassed()==0){
                    response.getWriter().print("NotPassed");
                }
            } else {
                response.getWriter().print("WrongPassword");
            }
        }else {
            response.getWriter().print("NoApply");
        }
    }
}
