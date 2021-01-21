package com.android.wx.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kai
 * on 2021/1/15
 */
@Entity
public class Table {
    @Id
    long id;
    String name;
    String orderNumber; //单号
    String persionNo;   //人数
    double amount1;
    double amount2;
    double amount3;
    String statue;      //状态
    double totalAmountPrice;
    String timeOld;     //时长
    String customerName; //顾客名字
    String floorName;    //楼层

    @Generated(hash = 523367520)
    public Table(long id, String name, String orderNumber, String persionNo, double amount1, double amount2, double amount3, String statue, double totalAmountPrice, String timeOld,
            String customerName, String floorName) {
        this.id = id;
        this.name = name;
        this.orderNumber = orderNumber;
        this.persionNo = persionNo;
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.amount3 = amount3;
        this.statue = statue;
        this.totalAmountPrice = totalAmountPrice;
        this.timeOld = timeOld;
        this.customerName = customerName;
        this.floorName = floorName;
    }

    @Generated(hash = 752389689)
    public Table() {
    }

    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOrderNumber() {
        return this.orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String getPersionNo() {
        return this.persionNo;
    }
    public void setPersionNo(String persionNo) {
        this.persionNo = persionNo;
    }
    public double getAmount1() {
        return this.amount1;
    }
    public void setAmount1(double amount1) {
        this.amount1 = amount1;
    }
    public double getAmount2() {
        return this.amount2;
    }
    public void setAmount2(double amount2) {
        this.amount2 = amount2;
    }
    public double getAmount3() {
        return this.amount3;
    }
    public void setAmount3(double amount3) {
        this.amount3 = amount3;
    }
    public String getStatue() {
        return this.statue;
    }
    public void setStatue(String statue) {
        this.statue = statue;
    }

    public double getTotalAmountPrice() {
        return totalAmountPrice;
    }

    public void setTotalAmountPrice(double totalAmountPrice) {
        this.totalAmountPrice = totalAmountPrice;
    }

    public String getTimeOld() {
        return timeOld;
    }

    public void setTimeOld(String timeOld) {
        this.timeOld = timeOld;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }
}
