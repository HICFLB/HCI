package edu.nju.onlineTicket.service.Impl;

import edu.nju.onlineTicket.dao.BankDao;
import edu.nju.onlineTicket.dao.UserDao;
import edu.nju.onlineTicket.model.Bank;
import edu.nju.onlineTicket.model.User;
import edu.nju.onlineTicket.service.UserService;
import edu.nju.onlineTicket.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asus1 on 2018/2/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BankDao bankDao;

    @Override
    public boolean doRegister(User user) {
        // 这里可以验证各字段是否为空

        //利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
        if(!user.getEmail().matches("^\\w+@(\\w+\\.)+\\w+$")){
            return false;
        }
        //生成激活码
        //保存成功则通过线程的方式给用户发送一封邮件
        if(userDao.save(user)>0){
            Bank bank = new Bank();
            bank.setUserName(user.getEmail());
            bank.setBalance(10000);
            bankDao.save(bank);
            new Thread(new MailUtil(user.getEmail(), user.getCode())).start();
            return true;
        }
        return false;
    }

    @Override
    public User findUser(String email) {
        return userDao.load(email);
    }

    @Override
    public boolean activeUser(String code) {
        return userDao.updateState(code);
    }

    @Override
    public boolean cancelUser(String email) {
        return userDao.cancelUser(email);
    }

    @Override
    public boolean modifyUserName(String email, String userName) {
        return userDao.modifyUserName(email, userName);
    }

    @Override
    public boolean modifyPassword(String email, String password) {
        return userDao.modifyPassword(email, password);
    }

    @Override
    public boolean exchangeCredit(String email, int credit) {
        boolean result = false;
        User user = userDao.load(email);
        double initCredit = user.getCredit();
        double newCredit = initCredit - credit;
        if(newCredit>=0){
            user.setCredit(newCredit);
            if(credit==500){
                user.setFiveOff(user.getFiveOff()+1);
            }else if(credit==1000){
                user.setTenOff(user.getTenOff()+1);
            }else if(credit==5000){
                user.setFiftyOff(user.getFiftyOff()+1);
            }else if(credit==10000){
                user.setHundredOff(user.getHundredOff()+1);
            }
            userDao.update(user);
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    @Override
    public boolean pay(String email, double orderPrice, int useDiscount) {
        Bank bank = bankDao.find(email);
        User user = userDao.load(email);
        Bank bank1 = bankDao.find("admin");
        boolean flag = false;
        double balance = bank.getBalance()-orderPrice;
        if(balance>=0){
            bank1.setBalance(bank1.getBalance()+orderPrice);
            bankDao.update(bank1);
            bank.setBalance(balance);
            bankDao.update(bank);
            user.setCredit(user.getCredit()+orderPrice*10);
            user.setConsumption(user.getConsumption()+orderPrice);
            if(useDiscount!=-1){
                if(useDiscount==0)
                    user.setFiveOff(user.getFiveOff()-1);
                if(useDiscount==1)
                    user.setTenOff(user.getTenOff()-1);
                if(useDiscount==2)
                    user.setFiftyOff(user.getFiftyOff()-1);
                if(useDiscount==3)
                    user.setHundredOff(user.getHundredOff()-1);
                user.setConsumption(user.getConsumption()+orderPrice);
                user.setCredit(user.getCredit()+orderPrice*10);
                if(user.getConsumption()>2000)
                    user.setLevel(2);
                else if(user.getConsumption()>1000)
                    user.setLevel(1);
                else
                    user.setLevel(0);
            }
            userDao.update(user);
            flag = true;
        }
        return flag;
    }

    @Override
    public void refund(double price, String email) {
        Bank bank = bankDao.find(email);
        bank.setBalance(bank.getBalance()+price);
        User user = userDao.load(email);
        user.setConsumption(user.getConsumption()-price);
        user.setCredit(user.getCredit()-price*10);
        userDao.update(user);
        bankDao.update(bank);
    }

    @Override
    public void payManager(double price) {
        Bank bank = bankDao.find("admin");
        bank.setBalance(bank.getBalance()+price);
        bankDao.update(bank);
    }

    @Override
    public void reduceManager(double price) {
        Bank bank = bankDao.find("admin");
        bank.setBalance(bank.getBalance()-price);
        bankDao.update(bank);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }
}
