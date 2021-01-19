package com.android.wx.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kai
 * on 2021/1/13
 */
@Entity
public class UserInfo {
    @Id
    private Long id;

    private String name;

    private int pwd;

    private int permission;

    @Generated(hash = 1361051995)
    public UserInfo(Long id, String name, int pwd, int permission) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.permission = permission;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPwd() {
        return this.pwd;
    }

    public void setPwd(int pwd) {
        this.pwd = pwd;
    }

    public int getPermission() {
        return this.permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }


}
