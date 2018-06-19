package edu.nju.onlineTicket.controller;

import edu.nju.onlineTicket.model.Order;
import edu.nju.onlineTicket.service.OrderService;
import edu.nju.onlineTicket.service.PerformanceService;
import edu.nju.onlineTicket.service.TicketService;
import edu.nju.onlineTicket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asus1 on 2018/3/19.
 */
@Controller
public class RealTimeTaskController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private PerformanceService performanceService;
    @Autowired
    private UserService userService;

    @Scheduled(cron="0/30 * * * * ? ") //间隔30秒执行
    public void cancelOrFinishedOrder() throws ParseException {
        //获取所有订单检测是否有用户在15分钟内未支付
        List<Order> orderList = orderService.getUnPayOrder();
        for(int i=0;i<orderList.size();i++){
            Order order = orderList.get(i);
            orderService.cancelOrder(order);
            if(!order.getTicketNo().equals("暂未配票")){
                String[] ticketNum = order.getTicketNo().split(";");
                ticketService.freeTickets(ticketNum);
                performanceService.addNumOfTickets(String.valueOf(order.getPerformanceID()),ticketNum.length);
            }
        }

        //获取所有订单检测是否有订单完成 即过了开始时间
        List<Order> orders = orderService.getUnFinishedOrder();
        for(int i=0;i<orders.size();i++){
            Order o = orders.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = sdf.parse(o.getBeginTime());
            Date now = new Date();
            long diff = now.getTime() - beginTime.getTime();
            if(diff>=0){
                orderService.updateOrderType(o.getOrderID() ,2);
            }
        }

        //下面是配票的逻辑
        List<Order> orderArray = orderService.getUnOccupiedTicketsOrder();
        for(int p=0;p<orderArray.size();p++){
            Order or = orderArray.get(p);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = sdf.parse(or.getBeginTime());
            Date now = new Date();
            long diff = beginTime.getTime() - now.getTime();
            if(diff/1000<=14*24*60*60) {
                if(ticketService.OccupiedTickets(or.getSituation(),or)){
                    System.out.println("配票成功!");
                }else{
                    //配票失败要退款 取消订单
                    if(orderService.updateOrderType(or.getOrderID(),4)){
                        userService.refund(or.getPrice(),or.getEmail());
                        userService.reduceManager(or.getPrice());
                    }
                }
            }

        }
    }

}
