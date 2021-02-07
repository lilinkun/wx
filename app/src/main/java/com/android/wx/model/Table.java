package com.android.wx.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * Created by kai
 * on 2021/1/15
 */
@Entity
public class Table implements Serializable{

    private String name;
    private String orderNumber; //单号
    private String persionNo;   //人数
    private double amount1;
    private double amount2;
    private double amount3;
    private String statue;      //状态
    private double totalAmountPrice;
    private long time;        //时间
    private String timeOld;     //时长
    private String customerName; //顾客名字
    private String floorName;    //楼层
    @Unique
    private String tableNum;    //桌号
    public static final long serialVersionUID = 12341;
    @Id(autoincrement = true)
    private Long tableid = null;
    private double minPrice = 0;

    @Generated(hash = 558630058)
    public Table(String name, String orderNumber, String persionNo, double amount1, double amount2, double amount3, String statue,
            double totalAmountPrice, long time, String timeOld, String customerName, String floorName, String tableNum, Long tableid,
            double minPrice) {
        this.name = name;
        this.orderNumber = orderNumber;
        this.persionNo = persionNo;
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.amount3 = amount3;
        this.statue = statue;
        this.totalAmountPrice = totalAmountPrice;
        this.time = time;
        this.timeOld = timeOld;
        this.customerName = customerName;
        this.floorName = floorName;
        this.tableNum = tableNum;
        this.tableid = tableid;
        this.minPrice = minPrice;
    }

    @Generated(hash = 752389689)
    public Table() {
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

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    public Long getTableid() {
        return this.tableid;
    }

    public void setTableid(Long tableid) {
        this.tableid = tableid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }
}
