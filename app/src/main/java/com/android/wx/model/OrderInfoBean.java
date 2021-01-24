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
 * @Author liguo
 * @Date 2021/1/22 16:42
 */
public class OrderInfoBean implements Serializable {

    String orderId;
    String order_time;
    public static final long serialVersionUID = 12331;

    public OrderInfoBean(String orderId,String order_time) {
        this.orderId = orderId;
        this.order_time = order_time;
    }

    public OrderInfoBean() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }
}
