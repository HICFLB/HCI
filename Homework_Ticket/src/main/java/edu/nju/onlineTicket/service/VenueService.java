package edu.nju.onlineTicket.service;

import edu.nju.onlineTicket.model.SubVenue;
import edu.nju.onlineTicket.model.Venue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus1 on 2018/3/2.
 */
public interface VenueService {
    boolean applyVenue(Venue venue);
    Venue findVenueByName(String venueName);
    Venue findVenue(String venueID);
    ArrayList<Venue> getAllApply();
    ArrayList<Venue> getApplyListByType(int type);
    ArrayList<SubVenue> getModifyApplyListByType(int type);
    List<Venue> getAllVenue();
    boolean checkApply(String venueID, int state);
    boolean passModifyVenue(String venueID, String subVenueID);
    boolean refuseModifyVenue(String venueID, String subVenueID, String reason);
    boolean modifyPassword(String venueID, String password);
    boolean modifyVenueInfo(String venueID, SubVenue subVenue);
    boolean resetModifyState(String venueID);
    double getFinance(int venueID);
    void balanceToVenue(int venueID,double totalPrice);
}
