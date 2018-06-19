package edu.nju.onlineTicket.service;

import edu.nju.onlineTicket.model.User;

import java.util.List;

/**
 * Created by asus1 on 2018/2/27.
 */
public interface UserService {
    boolean doRegister(User user);
    User findUser(String email);
    boolean activeUser(String code);
    boolean cancelUser(String email);
    boolean modifyUserName(String email, String userName);
    boolean modifyPassword(String email, String password);
    boolean exchangeCredit(String email, int type);
    boolean pay(String email, double orderPrice, int useDiscount);
    void refund(double price , String email);
    void payManager(double price);
    void reduceManager(double price);
    List<User> getAllUser();
}
