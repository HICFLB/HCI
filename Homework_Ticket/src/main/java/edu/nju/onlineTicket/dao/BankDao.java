package edu.nju.onlineTicket.dao;

import edu.nju.onlineTicket.model.Bank;

/**
 * Created by asus1 on 2018/3/1.
 */
public interface BankDao {
    int save(Bank bank);
    Bank find(String email);
    void update(Bank bank);
}
