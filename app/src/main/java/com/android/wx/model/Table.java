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
    String number;
    String persionNo;
    double amount1;
    double amount2;
    double amount3;
    int statue;
    double totalAmountPrice;

    @Generated(hash = 136024719)
    public Table(long id, String name, String number, String persionNo, double amount1, double amount2, double amount3, int statue, double totalAmountPrice) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.persionNo = persionNo;
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.amount3 = amount3;
        this.statue = statue;
        this.totalAmountPrice = totalAmountPrice;
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
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
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
    public int getStatue() {
        return this.statue;
    }
    public void setStatue(int statue) {
        this.statue = statue;
    }

    public double getTotalAmountPrice() {
        return totalAmountPrice;
    }

    public void setTotalAmountPrice(double totalAmountPrice) {
        this.totalAmountPrice = totalAmountPrice;
    }
}
