package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.Order;
import edu.nju.onlineTicket.model.Performance;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asus1 on 2018/3/14.
 */
@Controller
public class CheckOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private PerformanceService performanceService;

    @RequestMapping(value = "getOrderListByType")
    public void getOrderListByType(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int type = Integer.parseInt(request.getParameter("type"));
        String email = request.getParameter("email");
        List orders = orderService.getOrderListByType(type,email);
        JSONArray array = JSONArray.fromObject(orders);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(array.toString());
    }

    @RequestMapping(value = "cancelOrder")
    public void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String msg = "Fail";
        if(orderService.updateOrderType(orderID, 3)) {
            Order order = orderService.findOrder(orderID);
            if(!order.getTicketNo().equals("暂未配票")){
                String[] ticketNum = order.getTicketNo().split(";");
                ticketService.freeTickets(ticketNum);
                performanceService.addNumOfTickets(String.valueOf(order.getPerformanceID()),ticketNum.length);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//如2016-08-10 20:40
            Date orderTime = sdf.parse(order.getBeginTime());
            Date now = new Date();
            long diff = orderTime.getTime() - now.getTime();
            if(diff<86400000){
                userService.refund(order.getPrice()*0.8,order.getEmail());
                userService.reduceManager(order.getPrice()*0.8);
                order.setRefundPrice(order.getRefundPrice()+order.getPrice()*0.2);
                orderService.updateOrder(order);
                msg = "退订成功，扣除手续费："+order.getPrice()*0.2+"元！退款："+order.getPrice()*0.8+"元";
            }else{
                userService.refund(order.getPrice(),order.getEmail());
                userService.reduceManager(order.getPrice());
                msg = "退订成功，退款："+order.getPrice()+"元";
            }
            User user = userService.findUser(order.getEmail());
            request.getSession().setAttribute("user",user);
        }
        response.getWriter().print(msg);
    }
}
