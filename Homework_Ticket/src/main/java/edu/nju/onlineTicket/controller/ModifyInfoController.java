package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.SubVenue;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by asus1 on 2018/3/9.
 */
@Controller
public class ModifyInfoController {
    @Autowired
    UserService userService;

    @Autowired
    VenueService venueService;

    @RequestMapping(value = "modifyUserName")
    public void modifyUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String userName = request.getParameter("username");

        response.setCharacterEncoding("UTF-8");
        if(userService.modifyUserName(email,userName)){
            User user = (User)request.getSession().getAttribute("user");
            user.setUserName(userName);
            request.getSession().setAttribute("user",user);
            response.getWriter().print("Success");
        }
        else {
            response.getWriter().print("Fail");
        }
    }

    @RequestMapping(value = "cancelUser")
    public void cancelUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        response.setCharacterEncoding("UTF-8");
        if(userService.cancelUser(email))
            response.getWriter().print("Success");
        else
            response.getWriter().print("Fail");
    }

    @RequestMapping(value = "modifyPassword")
    public void modifyPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        response.setCharacterEncoding("UTF-8");
        if(userService.modifyPassword(email,password))
            response.getWriter().print("Success");
        else
            response.getWriter().print("Fail");
    }

    @RequestMapping(value = "creditExchange")
    public void creditExchange(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        int credit = Integer.parseInt(request.getParameter("credit"));
        if( userService.exchangeCredit(email,credit)){
            User user = userService.findUser(email);
            request.getSession().setAttribute("user",user);
            response.getWriter().print("Success");
        }
        else {
            response.getWriter().print("Fail");
        }
    }

    @RequestMapping(value = "modifyVenuePassword")
    public void modifyVenuePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String venueID = request.getParameter("venueID");
        String password = request.getParameter("password");
        response.setCharacterEncoding("UTF-8");
        if(venueService.modifyPassword(venueID,password)){
            Venue venue = venueService.findVenue(venueID);
            request.getSession().setAttribute("venue",venue);
            response.getWriter().print("Success");
        } else {
            response.getWriter().print("Fail");
        }
    }

    @RequestMapping(value = "modifyVenueInfo")
    public void modifyVenueInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String venueID = request.getParameter("venueID");
        String venueName = request.getParameter("venueName");
        String location = request.getParameter("location");
        String contact = request.getParameter("contact");
        String volume = request.getParameter("volume");
        SubVenue subVenue = new SubVenue();
        subVenue.setVenueID(Integer.parseInt(venueID));
        subVenue.setVenueName(venueName);
        subVenue.setLocation(location);
        subVenue.setContact(contact);
        subVenue.setTotalSeat(Integer.parseInt(volume));
        subVenue.setIsChecked(0);
        subVenue.setIsPassed(0);
        Date date=new Date();     //获取一个Date对象
        DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
        String modifyTime = simpleDateFormat.format(date);
        subVenue.setModifyTime(modifyTime);
        response.setCharacterEncoding("UTF-8");
        if(venueService.modifyVenueInfo(venueID,subVenue)){
            Venue venue = venueService.findVenue(venueID);
            request.getSession().setAttribute("venue",venue);
            response.getWriter().print("Success");
        }
        else
            response.getWriter().print("Fail");
    }

    @RequestMapping(value = "resetModifyState")
    public void resetModifyState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String venueID = request.getParameter("venueID");
        response.setCharacterEncoding("UTF-8");
        if(venueService.resetModifyState(venueID)) {
            response.getWriter().print("Success");
            Venue venue = venueService.findVenue(venueID);
            request.getSession().setAttribute("venue",venue);
        }
        else {
            response.getWriter().print("Fail");
        }
    }
}
