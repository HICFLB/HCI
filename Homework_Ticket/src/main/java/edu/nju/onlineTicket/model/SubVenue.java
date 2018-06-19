package edu.nju.onlineTicket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by asus1 on 2018/3/12.
 */
@Entity
public class SubVenue {
    private int subVenueID;
    private int venueID;      //7位识别码 系统分配 设置7位初始然后自增？
    private String venueName;//场馆名称
    private int totalSeat;
    private String location; //地点
    private String contact;  //联系电话
    private String modifyTime;
    private int isChecked;
    private int isPassed;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSubVenueID() {
        return subVenueID;
    }

    public void setSubVenueID(int subVenueID) {
        this.subVenueID = subVenueID;
    }

    public int getVenueID() {
        return venueID;
    }

    public void setVenueID(int venueID) {
        this.venueID = venueID;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public int getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(int isPassed) {
        this.isPassed = isPassed;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
