package edu.nju.onlineTicket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by asus1 on 2018/2/28.
 */
@Entity
public class Ticket {
    private String ticketID;      //票编号 由4位演出编号+5位座位号构成
    private int performanceID;      //演出编号
    private String seatType; //座位类型
    private int noOfSeats;     //座位编号
    private int isChecked;  //是否检票
    private int isOccupied;  //是否被占用
    private double singleCost; //一张票的单价

    @Id
    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public int getPerformanceID() {
        return performanceID;
    }

    public void setPerformanceID(int performanceID) {
        this.performanceID = performanceID;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public int getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(int isOccupied) {
        this.isOccupied = isOccupied;
    }

    public double getSingleCost() {
        return singleCost;
    }

    public void setSingleCost(double singleCost) {
        this.singleCost = singleCost;
    }
}
