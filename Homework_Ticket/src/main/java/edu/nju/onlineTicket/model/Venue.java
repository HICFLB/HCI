package edu.nju.onlineTicket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by asus1 on 2018/2/28.
 */
@Entity
public class Venue {
    private int idCode;      //7位识别码 系统分配 设置7位初始然后自增？
    private String venueName;//场馆名称
    private String password; //密码
    private String location; //地点
    private String contact;  //联系电话
    private String applyTime; //申请时间
    private int totalSeat;   //座位总数
    private long numOfBook;  //预定总数
    private long numOfUnsubscribe; //退订总数
    private double finance;  //财务
    private int isPassed;     //场馆是否通过申请
    private int isChecked;    //场馆是否被申请
    private int modifyState;  //四种状态 0未修改也就是最新的 1回复过了 2是没过 3是没审核
    private String refuseInfo; //修改信息拒绝理由

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public long getNumOfBook() {
        return numOfBook;
    }

    public void setNumOfBook(long numOfBook) {
        this.numOfBook = numOfBook;
    }

    public long getNumOfUnsubscribe() {
        return numOfUnsubscribe;
    }

    public void setNumOfUnsubscribe(long numOfUnsubscribe) {
        this.numOfUnsubscribe = numOfUnsubscribe;
    }

    public double getFinance() {
        return finance;
    }

    public void setFinance(double finance) {
        this.finance = finance;
    }

    public int getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(int isPassed) {
        this.isPassed = isPassed;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public int getModifyState() {
        return modifyState;
    }

    public void setModifyState(int modifyState) {
        this.modifyState = modifyState;
    }

    public String getRefuseInfo() {
        return refuseInfo;
    }

    public void setRefuseInfo(String refuseInfo) {
        this.refuseInfo = refuseInfo;
    }
}
