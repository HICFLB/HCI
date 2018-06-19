package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.Performance;
import edu.nju.onlineTicket.model.User;
import edu.nju.onlineTicket.model.Venue;
import edu.nju.onlineTicket.service.OrderService;
import edu.nju.onlineTicket.service.PerformanceService;
import edu.nju.onlineTicket.service.UserService;
import edu.nju.onlineTicket.service.VenueService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by asus1 on 2018/3/19.
 */
@Controller
public class StatisticsController {

    @Autowired
    private VenueService venueService;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @RequestMapping(value = "getVenueInfo")
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException,ParseException {
        String venueID = request.getParameter("venueID");
        Venue venue = venueService.findVenue(venueID);
        JSONArray object = JSONArray.fromObject(venue);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(object.toString());
    }

    @RequestMapping(value = "getPerformancesByVenue")
    public void getPerformancesByVenue(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int venueID = Integer.parseInt(request.getParameter("venueID"));
        List<Performance> performances = performanceService.getPerformancesByVenue(venueID);
        JSONArray array = JSONArray.fromObject(performances);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }
    @RequestMapping(value = "getPerformanceStatistics")
    public void getPerformanceStatistics(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int performanceID = Integer.parseInt(request.getParameter("performanceID"));
        double[] performanceStatistics = performanceService.getPerformanceStatistics(performanceID);
        JSONArray array = JSONArray.fromObject(performanceStatistics);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    @RequestMapping(value = "getPerformancesPriceByVenue")
    public void getPerformancesPriceByVenue(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int venueID = Integer.parseInt(request.getParameter("venueID"));
        double totalPrice =  venueService.getFinance(venueID);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(totalPrice);
    }

    @RequestMapping(value = "getAllVenue")
    public void getAllVenue(HttpServletRequest request, HttpServletResponse response) throws IOException,ParseException {
        List<Venue> venues = venueService.getAllVenue();
        JSONArray object = JSONArray.fromObject(venues);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(object.toString());
    }

    @RequestMapping(value = "getAllUser")
    public void getAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException,ParseException {
        List<User> users = userService.getAllUser();
        JSONArray object = JSONArray.fromObject(users);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(object.toString());
    }

    @RequestMapping(value = "getOnlinePrice")
    public void getOnlinePrice(HttpServletRequest request, HttpServletResponse response) throws IOException,ParseException {
        double onlinePrice = orderService.getOnlinePrice();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(onlinePrice);
    }

    @RequestMapping(value = "getOnSpotPrice")
    public void getOnSpotPrice(HttpServletRequest request, HttpServletResponse response) throws IOException,ParseException {
        double onSpotPrice = orderService.getOnSpotPrice();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(onSpotPrice);
    }
}
