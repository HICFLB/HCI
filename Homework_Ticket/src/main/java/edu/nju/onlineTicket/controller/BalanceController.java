package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.Performance;
import edu.nju.onlineTicket.service.PerformanceService;
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
public class BalanceController {
    @Autowired
    PerformanceService performanceService;

    @Autowired
    VenueService venueService;

    @RequestMapping(value = "getFinishedUnbalancedPerform")
    public void getFinishedUnbalancedPerform(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        List<Performance> performances = performanceService.getFinishedUnbalancedPerform();
        JSONArray array = JSONArray.fromObject(performances);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    @RequestMapping(value = "getPerformanceFinance")
    public void getPerformanceFinance(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int performanceID = Integer.parseInt(request.getParameter("performanceID"));
        double finance =  performanceService.getPerformFinance(performanceID);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(finance);
    }

    @RequestMapping(value = "balanceToVenue")
    public void balanceToVenue(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int venueID = Integer.parseInt(request.getParameter("venueID"));
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        int performanceID = Integer.parseInt(request.getParameter("performanceID"));
        venueService.balanceToVenue(venueID,totalPrice);
        performanceService.balancePerformance(performanceID);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("Success");
    }
}
