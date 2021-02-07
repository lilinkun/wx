package com.android.wx.model;

import android.view.Menu;

import com.android.wx.utils.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * create data 2021/1/19
 */
@Entity
public class MenuInfo{
    private String menuType;//菜品类型
    private String menuName;//菜单名
    private String menuIcon;//菜品图片
    private double menuPrice;//菜品价格
    private String menuId;//菜品编号
    private boolean isChoose;//点单时是否被选中
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> menuRemarks; //菜单备注
    private int menuFoodNum; //菜品数量
    private String orderId;
    private String payer;

    @Generated(hash = 1322188522)
    public MenuInfo(String menuType, String menuName, String menuIcon, double menuPrice,
            String menuId, boolean isChoose, List<String> menuRemarks, int menuFoodNum,
            String orderId, String payer) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.menuPrice = menuPrice;
        this.menuId = menuId;
        this.isChoose = isChoose;
        this.menuRemarks = menuRemarks;
        this.menuFoodNum = menuFoodNum;
        this.orderId = orderId;
        this.payer = payer;
    }

    @Generated(hash = 859137273)
    public MenuInfo() {
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(double menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public boolean getIsChoose() {
        return this.isChoose;
    }

    public void setIsChoose(boolean isChoose) {
        this.isChoose = isChoose;
    }

    public List<String> getMenuRemarks() {
        return menuRemarks;
    }

    public void setMenuRemarks(List<String> menuRemarks) {
        this.menuRemarks = menuRemarks;
    }

    public int getMenuFoodNum() {
        return menuFoodNum;
    }

    public void setMenuFoodNum(int menuFoodNum) {
        this.menuFoodNum = menuFoodNum;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }
}
