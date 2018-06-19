package edu.nju.onlineTicket.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by asus1 on 2018/2/28.
 */
@Entity
public class Bank {
    private String userName; //账号
    private double balance;  //余额

    @Id
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
