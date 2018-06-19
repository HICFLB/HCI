package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.Performance;
import edu.nju.onlineTicket.model.Ticket;
import edu.nju.onlineTicket.model.User;
import edu.nju.onlineTicket.service.OrderService;
import edu.nju.onlineTicket.service.PerformanceService;
import edu.nju.onlineTicket.service.TicketService;
import edu.nju.onlineTicket.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

/**
 * Created by asus1 on 2018/3/13.
 */
@Controller
public class PurchaseController {
    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "getPerformListByType")
    public void getPerformListByType(HttpServletRequest request, HttpServletResponse response) throws IOException ,ParseException{
        int type = Integer.parseInt(request.getParameter("type"));
        List<Performance> performances = performanceService.getPerformListByType(type);
        JSONArray array = JSONArray.fromObject(performances);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    @RequestMapping(value = "getPerformListByTypeVenue")
    public void getPerformListByTypeVenue(HttpServletRequest request, HttpServletResponse response) throws IOException ,ParseException{
        int type = Integer.parseInt(request.getParameter("type"));
        int venueID = Integer.parseInt(request.getParameter("venueID"));
        List<Performance> performances = performanceService.getPerformListByTypeVenue(type,venueID);
        JSONArray array = JSONArray.fromObject(performances);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }


    @RequestMapping(value = "getUserInfo")
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException ,ParseException{
        String email = request.getParameter("email");
        User user = userService.findUser(email);
        JSONArray object = JSONArray.fromObject(user);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(object.toString());
    }

    @RequestMapping(value = "payRightNow")
    public void payRightNow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int useDiscount = orderService.findOrder(orderID).getDiscount();
        String email = request.getParameter("email");
        double orderPrice = Double.parseDouble(request.getParameter("orderPrice"));
        response.setCharacterEncoding("UTF-8");
        if(userService.pay(email,orderPrice, useDiscount)){
            if(orderService.updateOrderType(orderID, 1)) {
                User user = userService.findUser(email);
                request.getSession().setAttribute("user",user);
                response.getWriter().print("Success");
            }
            else {
                response.getWriter().print("Fail");
            }
        }else{
            response.getWriter().print("NotEnough");
        }

    }

    @RequestMapping(value = "getTicketsByPerformance")
    public void getTicketsByPerformance(HttpServletRequest request, HttpServletResponse response) throws IOException ,ParseException{
        int performanceID = Integer.parseInt(request.getParameter("performanceID"));
        List<Ticket> tickets = ticketService.getTicketsByPerformance(performanceID);
        JSONArray array = JSONArray.fromObject(tickets);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    @RequestMapping(value = "purchaseRightNow")
    public void purchaseRightNow(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String performanceID = request.getParameter("performanceID");
        String email = request.getParameter("email");
        String[] buyType = request.getParameterValues("buy_type[]");
        String[] buyNum = request.getParameterValues("buy_num[]");
        double orderPrice = Double.parseDouble(request.getParameter("order_price"));
        int useDiscount = Integer.parseInt(request.getParameter("use_discount"));
        response.setCharacterEncoding("UTF-8");
        int numOfTickets = 0;
        for(int i=0;i<buyNum.length;i++){
            numOfTickets = numOfTickets + Integer.parseInt(buyNum[i]);
        }
        performanceService.minusNumOfTickets(performanceID,numOfTickets);
        int result = orderService.buyRightNow(performanceID,email,buyType,buyNum,orderPrice,useDiscount);
        response.getWriter().print(result);
    }

    @RequestMapping(value = "purchaseSelected")
    public void purchaseSelected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String performanceID = request.getParameter("performanceID");
        String email = request.getParameter("email");
        String situation = request.getParameter("situation");
        String seatNo = request.getParameter("seatNo");
        double orderPrice = Double.parseDouble(request.getParameter("order_price"));
        int useDiscount = Integer.parseInt(request.getParameter("use_discount"));
        DecimalFormat df1=new DecimalFormat("0000");
        DecimalFormat df2=new DecimalFormat("00000");
        String[] seatNum = seatNo.split(";");
        String ticketNo = "";
        int performID = Integer.parseInt(performanceID);
        for(int i=0;i<seatNum.length;i++){
            int noOfSeats = Integer.parseInt(seatNum[i]);
            ticketNo = ticketNo + df1.format(performID) + df2.format(noOfSeats)+";";
        }
        System.out.println(seatNum.length+"$$$$$$$$$$$$$$$$$$***");
        performanceService.minusNumOfTickets(performanceID,seatNum.length);
        ticketService.occupySeats(ticketNo);
        response.setCharacterEncoding("UTF-8");
        int result = orderService.buySelected(performanceID,email,situation,ticketNo,orderPrice,useDiscount);
        response.getWriter().print(result);
    }

    @RequestMapping(value = "purchaseOnSpot")
    public void purchaseOnSpot(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String performanceID = request.getParameter("performanceID");
        String seatNo = request.getParameter("seatNo");
        double orderPrice = Double.parseDouble(request.getParameter("order_price"));
        DecimalFormat df1=new DecimalFormat("0000");
        DecimalFormat df2=new DecimalFormat("00000");
        String[] seatNum = seatNo.split(";");
        String ticketNo = "";
        int performID = Integer.parseInt(performanceID);
        for(int i=0;i<seatNum.length;i++){
            int noOfSeats = Integer.parseInt(seatNum[i]);
            ticketNo = ticketNo + df1.format(performID) + df2.format(noOfSeats)+";";
        }
        performanceService.minusNumOfTickets(performanceID,seatNum.length);
        ticketService.occupySeats(ticketNo);
        response.setCharacterEncoding("UTF-8");
        userService.payManager(orderPrice);
        if(orderService.createSpotOrder(orderPrice, performanceID, ticketNo))
            response.getWriter().print("Success");
        else
            response.getWriter().print("Fail");
    }
}
