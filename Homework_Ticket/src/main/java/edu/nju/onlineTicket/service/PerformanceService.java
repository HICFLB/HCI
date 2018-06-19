package edu.nju.onlineTicket.service;

import edu.nju.onlineTicket.model.Performance;

import java.text.ParseException;
import java.util.List;

/**
 * Created by asus1 on 2018/3/2.
 */
public interface PerformanceService {
    boolean publishPerformance(Performance performance);
    int getPerformanceID(String venueID, String performanceName);
    List<Performance> getPerformListByType(int type) throws ParseException;
    List<Performance> getPerformListByVenue(int venueID) throws ParseException;
    List<Performance> getPerformListByTypeVenue(int type, int venueID) throws ParseException;
    List<Performance> getPerformancesByVenue(int venueID) throws ParseException;
    List<Performance> getFinishedUnbalancedPerform() throws ParseException;
    Performance getPerformance(String performanceID);
    void minusNumOfTickets(String performanceID, int num);
    void addNumOfTickets(String performanceID, int num);
    double[] getPerformanceStatistics(int performanceID);
    double getPerformFinance(int performanceID);
    void balancePerformance(int performanceID);
}
