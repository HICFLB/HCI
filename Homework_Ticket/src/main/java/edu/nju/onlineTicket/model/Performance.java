package edu.nju.onlineTicket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by asus1 on 2018/2/28.
 */
@Entity
public class Performance {
    private int performanceID;   //演出编号
    private int performanceType; //演出类型
    private String performanceName; //演出名称
    private String beginTime;    //演出开始时间
    private String endTime;      //演出结束时间
    private int venueID;         //场馆编号
    private String venueName;    //场馆名称
    private String description;  //表演的简单描述
    private int residueNum;      //剩余票数
    private String location;     //地点
    private String seatSituation;//字符串拼接 类似普通票-40-200+
    private double minPrice;     //最低票价
    private double maxPrice;     //最高票价
    private int rowNum;          //行数
    private int columnNum;       //列数
    private int totalTickets;    //总票数（总座位数）
    private int isBalanced;      //是否结算

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPerformanceID() {
        return performanceID;
    }

    public void setPerformanceID(int performanceID) {
        this.performanceID = performanceID;
    }

    public int getPerformanceType() {
        return performanceType;
    }

    public void setPerformanceType(int performanceType) {
        this.performanceType = performanceType;
    }

    public String getPerformanceName() {
        return performanceName;
    }

    public void setPerformanceName(String performanceName) {
        this.performanceName = performanceName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResidueNum() {
        return residueNum;
    }

    public void setResidueNum(int residueNum) {
        this.residueNum = residueNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSeatSituation() {
        return seatSituation;
    }

    public void setSeatSituation(String seatSituation) {
        this.seatSituation = seatSituation;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getIsBalanced() {
        return isBalanced;
    }

    public void setIsBalanced(int isBalanced) {
        this.isBalanced = isBalanced;
    }
}
