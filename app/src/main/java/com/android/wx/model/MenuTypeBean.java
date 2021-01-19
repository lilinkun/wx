package com.android.wx.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MenuTypeBean {
    private String MenuType;
    private String MenuTypeId;

    @Generated(hash = 873160422)
    public MenuTypeBean(String MenuType, String MenuTypeId) {
        this.MenuType = MenuType;
        this.MenuTypeId = MenuTypeId;
    }

    @Generated(hash = 605699688)
    public MenuTypeBean() {
    }

    public String getMenuType() {
        return MenuType;
    }

    public void setMenuType(String menuType) {
        MenuType = menuType;
    }

    public String getMenuTypeId() {
        return MenuTypeId;
    }

    public void setMenuTypeId(String menuTypeId) {
        MenuTypeId = menuTypeId;
    }
}
