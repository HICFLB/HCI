package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.Performance;
import edu.nju.onlineTicket.service.PerformanceService;
import edu.nju.onlineTicket.service.TicketService;
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
 * Created by asus1 on 2018/3/13.
 */
@Controller
public class CheckTicketController {
    @Autowired
    PerformanceService performanceService;

    @Autowired
    TicketService ticketService;
    @RequestMapping(value = "getPerformListByVenue")
    public void getPerformListByVenue(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int venueID = Integer.parseInt(request.getParameter("venueID"));
        List<Performance> performances = performanceService.getPerformListByVenue(venueID);
        JSONArray array = JSONArray.fromObject(performances);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    @RequestMapping(value = "getPerformance")
    public void getPerformance(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String performanceID = request.getParameter("performanceID");
        Performance performance = performanceService.getPerformance(performanceID);
        JSONArray object = JSONArray.fromObject(performance);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(object.toString());
    }

    @RequestMapping(value = "checkTickets")
    public void checkTickets(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String ticketID = request.getParameter("ticketID");
        String result = ticketService.checkTicket(ticketID);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(result);
    }

    @RequestMapping(value = "getCheckedNum")
    public void getCheckedNum(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String performanceID = request.getParameter("performanceID");
        long checkedNum = ticketService.getCheckedNum(performanceID);
        System.out.println(checkedNum+"********");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(checkedNum);
    }


}
