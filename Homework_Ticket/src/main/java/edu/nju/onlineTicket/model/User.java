package edu.nju.onlineTicket.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by asus1 on 2018/2/25.
 */
@Entity
public class User {
    private String email;    //邮箱
    private String userName; //用户名
    private String password; //密码
    private int state;       //用户激活状态 0未激活 1激活
    private String code;     //激活码
    private int level;       //级别 0铜牌 1银牌 2金牌
    private double credit;   //积分
    private int isLogOff;    //用户是否注销 0未注销 1已注销
    private int fiveOff;     //满50减5
    private int tenOff; //满100减10
    private int fiftyOff; //满500减50
    private int hundredOff;   //满一千减100
    private double consumption; //消费金额


    @Id
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getIsLogOff() {
        return isLogOff;
    }

    public void setIsLogOff(int isLogOff) {
        this.isLogOff = isLogOff;
    }

    public int getFiveOff() {
        return fiveOff;
    }

    public void setFiveOff(int fiveOff) {
        this.fiveOff = fiveOff;
    }

    public int getTenOff() {
        return tenOff;
    }

    public void setTenOff(int tenOff) {
        this.tenOff = tenOff;
    }

    public int getFiftyOff() {
        return fiftyOff;
    }

    public void setFiftyOff(int fiftyOff) {
        this.fiftyOff = fiftyOff;
    }

    public int getHundredOff() {
        return hundredOff;
    }

    public void setHundredOff(int hundreOff) {
        this.hundredOff = hundreOff;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }
}
