package com.android.wx.model;

import android.view.Menu;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * create data 2021/1/19
 */
@Entity
public class MenuInfo {
    private String menuType;//菜品类型
    private String menuName;//菜单名
    private String menuIcon;//菜品图片
    private String menuPrice;//菜品价格
    private String menuId;//菜品编号

    @Generated(hash = 1199045594)
    public MenuInfo(String menuType, String menuName, String menuIcon, String menuPrice,
            String menuId) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.menuPrice = menuPrice;
        this.menuId = menuId;
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

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
