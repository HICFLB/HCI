package edu.nju.onlineTicket.service.Impl;

import edu.nju.onlineTicket.dao.OrderDao;
import edu.nju.onlineTicket.dao.PerformanceDao;
import edu.nju.onlineTicket.dao.TicketDao;
import edu.nju.onlineTicket.dao.UserDao;
import edu.nju.onlineTicket.model.Order;
import edu.nju.onlineTicket.model.Performance;
import edu.nju.onlineTicket.model.Ticket;
import edu.nju.onlineTicket.model.User;
import edu.nju.onlineTicket.service.OrderService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asus1 on 2018/3/2.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PerformanceDao performanceDao;

    @Autowired
    private TicketDao ticketDao;

    @Override
    public Order findOrder(int orderID) {
        return orderDao.load(orderID);
    }

    @Override
    public List getOrderListByType(int type, String email) throws ParseException {
        List<Order> orders = orderDao.findPersonalOrderByType(type, email);
        return orders;
    }

    @Override
    public List getUnPayOrder() {
        return orderDao.findOrderByType(0);
    }

    @Override
    public List getUnFinishedOrder() {
        return orderDao.findOrderByType(1);
    }

    @Override
    public List getUnOccupiedTicketsOrder() {
        return orderDao.findUnOccupiedOrder();
    }

    @Override
    public void cancelOrder(Order order) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date orderTime = sdf.parse(order.getOrderTime());
        Date now = new Date();
        long diff = now.getTime() - orderTime.getTime();
        if((diff/1000)>15*60){
            orderDao.cancelOrder(order);
        }
    }

    @Override
    public boolean updateOrderType(int orderID, int type) {
        return  orderDao.updateType(orderID,type);
    }

    @Override
    public int buyRightNow(String performanceID, String email, String[] buyType, String[] buyNum, double orderPrice, int useDiscount) throws ParseException {
        Performance performance = performanceDao.find(Integer.parseInt(performanceID));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Order order = new Order();
        order.setPerformanceID(Integer.parseInt(performanceID));
        order.setPerformanceName(performance.getPerformanceName());
        order.setType(0);
        order.setEmail(email);
        order.setBeginTime(performance.getBeginTime());
        order.setEndTime(performance.getEndTime());
        order.setIsSelect(0);
        order.setRefundPrice(0);
        order.setPrice(orderPrice);
        order.setDiscount(useDiscount);

        Date date=new Date();     //获取一个Date对象
        DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
        String orderTime = simpleDateFormat.format(date);
        order.setOrderTime(orderTime);
        Date beginTime = sdf.parse(performance.getBeginTime());
        long diff = beginTime.getTime() - date.getTime();
        System.out.println("#########"+diff+"################");
        if(diff/1000<=1209600){
            //这里要配票
            String ticketNo = "";
            for(int j=0;j<buyType.length;j++){
                List<Ticket> tickets = ticketDao.findFreeTickets(performance.getPerformanceID(),buyType[j]);
                if(tickets.size()>=Integer.parseInt(buyNum[j])){
                    for(int p=0;p<Integer.parseInt(buyNum[j]);p++){
                        //把票站上 然后得到ticketNo
                        Ticket ticket = tickets.get(p);
                        ticket.setIsOccupied(1);
                        ticketDao.updateTicket(ticket);
                        ticketNo = ticketNo +ticket.getTicketID() + ";";
                    }
                }else{
                    return -2;
                }
            }
            order.setTicketNo(ticketNo);
        }else{  //如果没到开始两周前
            order.setTicketNo("暂未配票");
        }
        String situation = "";
        for(int i=0;i<buyType.length;i++){
            situation = situation+buyType[i]+" "+buyNum[i]+"张;";
        }
        order.setSituation(situation);
        if(orderDao.save(order)>0)
            return orderDao.findPersonalOrderByPerform(email, Integer.parseInt(performanceID), orderPrice).getOrderID();
        else
            return -1;
    }

    @Override
    public int buySelected(String performanceID, String email, String situation, String ticketNo, double orderPrice, int useDiscount) {
        Performance performance = performanceDao.find(Integer.parseInt(performanceID));
        Order order = new Order();
        order.setPerformanceID(Integer.parseInt(performanceID));
        order.setPerformanceName(performance.getPerformanceName());
        order.setType(0);
        order.setEmail(email);
        order.setRefundPrice(0);
        order.setBeginTime(performance.getBeginTime());
        order.setEndTime(performance.getEndTime());
        order.setIsSelect(0);
        order.setPrice(orderPrice);
        order.setDiscount(useDiscount);
        Date date=new Date();     //获取一个Date对象
        DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
        String orderTime = simpleDateFormat.format(date);
        order.setOrderTime(orderTime);
        order.setSituation(situation);
        order.setTicketNo(ticketNo);
        if(orderDao.save(order)>0)
            return  orderDao.findPersonalOrderByPerform(email, Integer.parseInt(performanceID), orderPrice).getOrderID();
        else
            return -1;
    }

    @Override
    public boolean createSpotOrder(double price, String performanceID, String ticketNo) {
        Performance performance = performanceDao.find(Integer.parseInt(performanceID));
        Order order = new Order();
        order.setPerformanceID(Integer.parseInt(performanceID));
        order.setPerformanceName(performance.getPerformanceName());
        order.setType(2);
        order.setBeginTime(performance.getBeginTime());
        order.setEndTime(performance.getEndTime());
        order.setIsSelect(0);
        order.setRefundPrice(0);
        order.setPrice(price);
        order.setDiscount(-1);
        Date date=new Date();     //获取一个Date对象
        DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
        String orderTime = simpleDateFormat.format(date);
        order.setOrderTime(orderTime);
        order.setTicketNo(ticketNo);
        return orderDao.save(order)>0;
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.update(order);
    }

    @Override
    public double getOnlinePrice() {
        List<Performance> performances = performanceDao.findAll();
        double onlinePrice = 0;
        for(int i=0;i<performances.size();i++){
            Performance performance = performances.get(i);
            if(performance.getIsBalanced()==1){
                List<Order> orders = orderDao.findOrderByPerform(performance.getPerformanceID());
                for(int j=0;j<orders.size();j++){
                    if(orders.get(j).getEmail()!=""&&orders.get(j).getType()==2){
                        onlinePrice = onlinePrice + orders.get(j).getPrice();
                    }
                    if(orders.get(j).getType()==3){
                        onlinePrice = onlinePrice + orders.get(j).getRefundPrice();
                    }
                }
            }
        }
        return onlinePrice;
    }

    @Override
    public double getOnSpotPrice() {
        List<Performance> performances = performanceDao.findAll();
        double onSpotPrice = 0;
        for(int i=0;i<performances.size();i++){
            Performance performance = performances.get(i);
            if(performance.getIsBalanced()==1){
                List<Order> orders = orderDao.findOrderByPerform(performance.getPerformanceID());
                for(int j=0;j<orders.size();j++){
                    if(orders.get(j).getEmail()==null){
                        onSpotPrice = onSpotPrice + orders.get(j).getPrice();
                    }
                }
            }
        }
        return onSpotPrice;
    }
}
