package edu.nju.onlineTicket.service.Impl;

import edu.nju.onlineTicket.dao.OrderDao;
import edu.nju.onlineTicket.dao.PerformanceDao;
import edu.nju.onlineTicket.model.Order;
import edu.nju.onlineTicket.model.Performance;
import edu.nju.onlineTicket.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asus1 on 2018/3/2.
 */
@Service
public class PerformanceServiceImpl implements PerformanceService {
    @Autowired
    private PerformanceDao performanceDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public boolean publishPerformance(Performance performance) {
        return performanceDao.save(performance)>0;
    }

    @Override
    public int getPerformanceID(String venueID, String performanceName) {
        return performanceDao.getPerformanceID(venueID,performanceName);

    }

    @Override
    public List<Performance> getPerformListByType(int type) throws ParseException {
        List<Performance> performances = new ArrayList<>();
        List<Performance> deletPerformances = new ArrayList<>();
        if(type==0) {
            performances = performanceDao.findAll();
        }else {
            performances = performanceDao.findPerformanceByType(type-1);
        }

        for(int i=0;i<performances.size();i++){
            Performance performance = performances.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = sdf.parse(performance.getBeginTime());
            Date now = new Date();
            long diff = beginTime.getTime() - now.getTime() ;
            if(diff<0)
                deletPerformances.add(performance);
        }
        performances.removeAll(deletPerformances);
        return performances;
    }

    @Override
    public List<Performance> getPerformListByVenue(int venueID) throws ParseException {
        List<Performance> performances = performanceDao.findPerformanceByVenue(venueID);
        List<Performance> delete = new ArrayList<>();
        for(int i=0;i<performances.size();i++){
            System.out.println(performances.get(i).getBeginTime());
            Performance performance = performances.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = sdf.parse(performance.getBeginTime());
            Date now = new Date();
            long diff = beginTime.getTime() - now.getTime() ;
            System.out.println(beginTime+"###"+now+"###"+diff);
            //这里要求时间差为24小时
            if(diff<=0||diff>86400000)
                delete.add(performance);
        }
        performances.removeAll(delete);
        return performances;
    }

    @Override
    public List<Performance> getPerformListByTypeVenue(int type, int venueID) throws ParseException {
        List<Performance> performances = new ArrayList<>();
        if(type==0) {
            performances = performanceDao.findPerformanceByVenue(venueID);
        }else {
            performances = performanceDao.findPerformanceByTypeVenue(type-1,venueID);
        }
        List<Performance> delet = new ArrayList<>();
        for(int i=0;i<performances.size();i++){
            Performance performance = performances.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginTime = sdf.parse(performance.getBeginTime());
            Date now = new Date();
            long diff = beginTime.getTime() - now.getTime() ;
            if(diff<0)
                delet.add(performance);
        }
        performances.removeAll(delet);
        return performances;
    }

    @Override
    public List<Performance> getPerformancesByVenue(int venueID) {
        List<Performance> performances = performanceDao.findPerformanceByVenue(venueID);
        return performances;
    }

    @Override
    public List<Performance> getFinishedUnbalancedPerform() throws ParseException {
        List<Performance> performances = performanceDao.findAll();
        List<Performance> deletPerformances = new ArrayList<>();
        for(int i=0;i<performances.size();i++){
            Performance performance = performances.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date endTime = sdf.parse(performance.getEndTime());
            Date now = new Date();
            long diff = endTime.getTime() - now.getTime() ;
            System.out.println(endTime.getTime()+"结束时间"+now.getTime()+"现在时间"+diff+"时间差"+"$$$"+performance.getPerformanceName());
            if(diff<=0){  //演出已经结束
                if(performance.getIsBalanced()==1) {
                    deletPerformances.add(performance);
                }
            }else{
                deletPerformances.add(performance);
            }
        }
        performances.removeAll(deletPerformances);
        return performances;
    }

    @Override
    public Performance getPerformance(String performanceID) {
        return performanceDao.find(Integer.parseInt(performanceID));
    }

    @Override
    public void minusNumOfTickets(String performanceID, int num) {
        Performance performance = performanceDao.find(Integer.parseInt(performanceID));
        performance.setResidueNum(performance.getResidueNum()-num);
        performanceDao.update(performance);
    }

    @Override
    public void addNumOfTickets(String performanceID, int num) {
        Performance performance = performanceDao.find(Integer.parseInt(performanceID));
        performance.setResidueNum(performance.getResidueNum()+num);
        performanceDao.update(performance);
    }

    @Override
    public double[] getPerformanceStatistics(int performanceID) {
        double[] result = new double[4];
/*        long numOfBook = orderDao.getBookNum(performanceID);
        long numOfUnsubcribe = orderDao.getUnsubscribeNum(performanceID);
        result[0]= String.valueOf(numOfBook);
        result[1]=String.valueOf(numOfUnsubcribe);*/
        List<Order> orders = orderDao.findOrderByPerform(performanceID);
        if(orders.size()>0){
            double numOfBook = 0;
            double numOfUnsubscribe = 0;
            double priceOfBook = 0;
            double priceOfUnsubsribe = 0;
            for(int i=0;i<orders.size();i++){
                Order order = orders.get(i);
                if(order.getType()!=4&&order.getType()!=3){  //如果是预订订单
                    if(order.getType()!=0){ //用户未支付
                        priceOfBook = priceOfBook + order.getPrice()*0.95;
                    }
                    numOfBook = numOfBook + 1;
                }else{
                    if(order.getType()==3){
                        numOfUnsubscribe = numOfUnsubscribe+1;  //如果是退订订单
                        priceOfUnsubsribe = priceOfUnsubsribe + order.getRefundPrice()*0.95;
                    }
                }
            }
            result[0] = numOfBook;
            result[1] = numOfUnsubscribe;
            result[2] = priceOfBook;
            result[3] = priceOfUnsubsribe;
        }else{
            result[0] = 0;
            result[1] = 0;
            result[2] = 0;
            result[3] = 0;
        }
        return result;
    }

    @Override
    public double getPerformFinance(int performanceID) {
        List<Order> orders = orderDao.findOrderByPerform(performanceID);
        double finance = 0 ;
        for(int i=0;i<orders.size();i++){
            Order order = orders.get(i);
            if(order.getType()==3){
                finance = finance + order.getRefundPrice();
            }
            if(order.getType()==2){
                finance = finance + order.getPrice();
            }
        }
        return finance;
    }

    @Override
    public void balancePerformance(int performanceID) {
        Performance performance = performanceDao.find(performanceID);
        performance.setIsBalanced(1);
        performanceDao.update(performance);
    }
}
