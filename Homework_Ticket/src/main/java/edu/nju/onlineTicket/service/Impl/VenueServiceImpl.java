package edu.nju.onlineTicket.service.Impl;

import edu.nju.onlineTicket.dao.BankDao;
import edu.nju.onlineTicket.dao.OrderDao;
import edu.nju.onlineTicket.dao.PerformanceDao;
import edu.nju.onlineTicket.dao.VenueDao;
import edu.nju.onlineTicket.model.*;
import edu.nju.onlineTicket.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus1 on 2018/3/2.
 */
@Service
public class VenueServiceImpl implements VenueService {
    @Autowired
    private VenueDao venueDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BankDao bankDao;

    @Autowired
    private PerformanceDao performanceDao;

    @Override
    public boolean applyVenue(Venue venue) {
        return venueDao.save(venue) > 0;
    }

    @Override
    public Venue findVenueByName(String venueName) {
        return venueDao.findVenue(venueName);
    }

    @Override
    public Venue findVenue(String venueID) {
        return venueDao.load(venueID);
    }

    @Override
    public ArrayList<Venue> getAllApply() {
        return (ArrayList<Venue>) venueDao.findAll();
    }

    @Override
    public ArrayList<Venue> getApplyListByType(int type) {
        ArrayList<Venue> venues = new ArrayList<>();
        if(type==0) {
            venues = (ArrayList<Venue>) venueDao.findAll();
        }else if(type==1){
            venues = (ArrayList<Venue>) venueDao.findNotChecked();
        }else if(type==2){
            venues = (ArrayList<Venue>) venueDao.findPassedList();
        }else if(type==3){
            venues = (ArrayList<Venue>) venueDao.findRefusedList();
        }
        return venues;
    }

    @Override
    public ArrayList<SubVenue> getModifyApplyListByType(int type) {
        ArrayList<SubVenue> subVenues = new ArrayList<>();
        if(type==0) {
            subVenues = (ArrayList<SubVenue>) venueDao.findAllSubVenue();
        }else if(type==1){
            subVenues = (ArrayList<SubVenue>) venueDao.findSubNotChecked();
        }else if(type==2){
            subVenues = (ArrayList<SubVenue>) venueDao.findSubPassedList();
        }else if(type==3){
            subVenues = (ArrayList<SubVenue>) venueDao.findSubRefusedList();
        }
        return subVenues;
    }

    @Override
    public List<Venue> getAllVenue() {
        return venueDao.findAll();
    }

    @Override
    public boolean checkApply(String venueID, int state) {
        return venueDao.updateVenue(venueID, state);
    }

    @Override
    public boolean passModifyVenue(String venueID, String subVenueID) {
        boolean result = false;
        if(venueDao.updateSubVenue(subVenueID,1)){ //副表中修改记录改为已通过
            SubVenue subVenue = venueDao.findSubVenue(subVenueID);
            Venue venue = venueDao.load(venueID);
            venue.setVenueName(subVenue.getVenueName());
            venue.setTotalSeat(subVenue.getTotalSeat());
            venue.setLocation(subVenue.getLocation());
            venue.setContact(subVenue.getContact());
            venue.setModifyState(1);  //设置状态为修改通过
            venueDao.update(venue);   //更新场馆信息
            result = true;
        }
        return result;
    }

    @Override
    public boolean refuseModifyVenue(String venueID, String subVenueID, String reason) {
        boolean result = false;
        if(venueDao.updateSubVenue(subVenueID,0)){ //副表中修改记录改为已拒绝
            Venue venue = venueDao.load(venueID);
            venue.setRefuseInfo(reason);
            venue.setModifyState(2); //设置状态为已拒绝并添加拒绝理由
            venueDao.update(venue);
            result = true;
        }
        return result;
    }

    @Override
    public boolean modifyPassword(String venueID, String password) {
        return venueDao.modifyPassword(venueID, password);
    }

    @Override
    public boolean modifyVenueInfo(String venueID, SubVenue subVenue) {
        if(venueDao.addSubVenue(subVenue)&&venueDao.updateModifyState(venueID, 3))
            return true;
        else
            return false;
    }

    @Override
    public boolean resetModifyState(String venueID) {
        return venueDao.updateModifyState(venueID,0);
    }

    @Override
    public double getFinance(int venueID) {
        List<Performance> performances = performanceDao.findPerformanceByVenue(venueID);
        List<Order> orders = new ArrayList<>();
        double finance = 0;
        for(int i =0;i<performances.size();i++){
            List<Order> o = orderDao.findOrderByPerform(performances.get(i).getPerformanceID());
            orders.addAll(o);
        }
        for(int j=0;j<orders.size();j++){
            Order order = orders.get(j);
            if(order.getType()==3){
                finance = finance + order.getRefundPrice();
            }
            if(order.getType()==1||order.getType()==2){
                finance = finance + order.getPrice();
            }
        }
        return finance*0.95;
    }

    @Override
    public void balanceToVenue(int venueID, double totalPrice) {
        Venue venue = venueDao.load(String.valueOf(venueID));
        venue.setFinance(venue.getFinance()+totalPrice*0.95);
        venueDao.update(venue);
        Bank bank = bankDao.find("admin");
        bank.setBalance(bank.getBalance()-totalPrice*0.05);
        bankDao.update(bank);
    }
}
