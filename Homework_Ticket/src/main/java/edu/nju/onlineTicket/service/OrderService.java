package edu.nju.onlineTicket.service;

import edu.nju.onlineTicket.model.Order;

import java.text.ParseException;
import java.util.List;

/**
 * Created by asus1 on 2018/3/2.
 */
public interface OrderService {
    Order findOrder(int orderID);
    List getOrderListByType(int type, String email)  throws ParseException;
    List getUnPayOrder();
    List getUnFinishedOrder();
    List getUnOccupiedTicketsOrder();
    void cancelOrder(Order order) throws ParseException;
    boolean updateOrderType(int orderID ,int type);
    int buyRightNow(String performanceID, String email, String[] buyType, String[] buyNum, double orderPrice, int useDiscount) throws ParseException;
    int buySelected(String performanceID, String email, String situation, String ticketNo, double orderPrice, int useDiscount);
    boolean createSpotOrder(double price, String performanceID, String ticketNo);
    void updateOrder(Order order);
    double getOnlinePrice();
    double getOnSpotPrice();
}
