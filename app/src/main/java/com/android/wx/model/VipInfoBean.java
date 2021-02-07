package com.android.wx.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @ClassName VipInfoBean
 * @Description TODO
 * @Author Administrator
 * @Date 2021/1/28 0:55
 */
@Entity
public class VipInfoBean {

    private String cardId; //会员号
    private String userName; //名字
    private String balance; //余额
    private String integral;//积分
    private String startTime;  //开始时间
    private String registerDate; //有效时间
    private String name;    //姓名
    private String surName; //姓氏
    private String birthday; //生日
    private String telphone;  //电话
    private String email;      //邮箱
    private String address;    //地址
    private String operator;    //操作者
    private double reCharge;    //充值
    private double consunption; //消费

    @Generated(hash = 1128055451)
    public VipInfoBean(String cardId, String userName, String balance, String integral, String startTime, String registerDate, String name, String surName, String birthday,
            String telphone, String email, String address, String operator, double reCharge, double consunption) {
        this.cardId = cardId;
        this.userName = userName;
        this.balance = balance;
        this.integral = integral;
        this.startTime = startTime;
        this.registerDate = registerDate;
        this.name = name;
        this.surName = surName;
        this.birthday = birthday;
        this.telphone = telphone;
        this.email = email;
        this.address = address;
        this.operator = operator;
        this.reCharge = reCharge;
        this.consunption = consunption;
    }

    @Generated(hash = 1623856703)
    public VipInfoBean() {
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public double getReCharge() {
        return reCharge;
    }

    public void setReCharge(double reCharge) {
        this.reCharge = reCharge;
    }

    public double getConsunption() {
        return consunption;
    }

    public void setConsunption(double consunption) {
        this.consunption = consunption;
    }
}
