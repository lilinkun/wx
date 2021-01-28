package com.android.wx.model;

import com.android.wx.utils.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName OrderInfoBean
 * @Description 订单实体
 * @Date 2021/1/22 16:42
 */
@Entity
public class OrderInfoBean {

    String orderId;
    String orderTime;
    int type;
    int total;
    double totalPrice;

    @Generated(hash = 748250291)
    public OrderInfoBean(String orderId, String orderTime, int type, int total, double totalPrice) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.type = type;
        this.total = total;
        this.totalPrice = totalPrice;
    }

    @Generated(hash = 66300297)
    public OrderInfoBean() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
