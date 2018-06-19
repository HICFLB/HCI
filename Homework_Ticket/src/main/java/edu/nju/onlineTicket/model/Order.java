package edu.nju.onlineTicket.model;



import javax.persistence.*;

/**
 * Created by asus1 on 2018/2/28.
 */
@Entity
@Table(name="`order`")
public class Order {
    private int orderID;        //订单编号
    private int performanceID;         //演出编码
    private String orderTime;
    private String beginTime;
    private String endTime;
    private String email;       //邮箱
    private String performanceName; //演出名称
    private int type;           //订单类型 0未完成未支付 1已支付 2已检票  3退订
    private int isSelect;       //是否选座 选座最多6张票 未选最多20张
    private String situation;   //选座情况 很复杂 拼字符串？？？？
    private double price;       //订单金额
    private String ticketNo;    //票号 拼字符串好了
    private int discount;       //使用优惠券 对应-1 0 1 2 3
    private double refundPrice; //退订金额

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPerformanceID() {
        return performanceID;
    }

    public void setPerformanceID(int performanceID) {
        this.performanceID = performanceID;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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

    public String getPerformanceName() {
        return performanceName;
    }

    public void setPerformanceName(String performanceName) {
        this.performanceName = performanceName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(double refundPrice) {
        this.refundPrice = refundPrice;
    }
}
