package edu.nju.onlineTicket.dao;

import edu.nju.onlineTicket.model.Performance;

import java.util.List;

/**
 * Created by asus1 on 2018/3/1.
 */
public interface PerformanceDao {
    int save(Performance performance);
    int getPerformanceID(String venueID, String performanceName);
    List<Performance> findAll();
    List<Performance> findPerformanceByType(int type);
    List<Performance> findPerformanceByVenue(int venueID);
    List<Performance> findPerformanceByTypeVenue(int type,int venueID);
    Performance find(int performanceID);
    void update(Performance performance);
}
