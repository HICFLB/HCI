package edu.nju.onlineTicket.dao;

import edu.nju.onlineTicket.model.Order;
import edu.nju.onlineTicket.model.Performance;

import java.util.List;

/**
 * Created by asus1 on 2018/3/1.
 */
public interface OrderDao {
    int save(Order order);
    void update(Order order);
    Order load(int orderID);
    List findPersonalOrderByType(int type, String email);
    List findOrderByType(int type);
    List findUnOccupiedOrder();
    Order findPersonalOrderByPerform(String email,int performanceID,double orderPrice);
    void cancelOrder(Order order);
    boolean updateType(int orderID, int type);
/*    long getBookNum(int performanceID);
    long getUnsubscribeNum(int performanceID);*/
    List findOrderByPerform(int performanceID);
    List findAll();
}
