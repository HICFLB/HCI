package edu.nju.onlineTicket.dao.Impl;

import edu.nju.onlineTicket.dao.BankDao;
import edu.nju.onlineTicket.dao.BaseDao;
import edu.nju.onlineTicket.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by asus1 on 2018/3/1.
 */
@Repository
public class BankDaoImpl implements BankDao{
    @Autowired
    private BaseDao baseDao;

    @Override
    public int save(Bank bank) {
        return baseDao.save(bank);
    }

    @Override
    public Bank find(String email) {
        return (Bank) baseDao.load(Bank.class, email);
    }

    @Override
    public void update(Bank bank) {
        baseDao.update(bank);
    }
}
