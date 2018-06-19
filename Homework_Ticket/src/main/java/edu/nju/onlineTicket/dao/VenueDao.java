package edu.nju.onlineTicket.dao;

import edu.nju.onlineTicket.model.SubVenue;
import edu.nju.onlineTicket.model.Venue;

import java.util.List;


/**
 * Created by asus1 on 2018/3/1.
 */
public interface VenueDao {
    int save(Venue venue);
    void update(Venue venue);
    Venue findVenue(String venueName);
    Venue load(String venueID);
    SubVenue findSubVenue(String subVenueID);
    List findAll();
    List findAllSubVenue();
    List findNotChecked();
    List findSubNotChecked();
    List findPassedList();
    List findSubPassedList();
    List findRefusedList();
    List findSubRefusedList();
    boolean updateVenue(String venueID, int state);
    boolean updateSubVenue(String subVenueID, int state);
    boolean modifyPassword(String venueID, String password);
    boolean addSubVenue(SubVenue subVenue);
    boolean updateModifyState(String venueID, int state);
}
