package com.android.wx.model;

/**
 * @ClassName SettingChildBean
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/3 14:03
 */
public class SettingChildBean {
    private String name;
    private int id;
    private String iconfont;

    public SettingChildBean(String name,int id,String iconfont){
        this.name=name;
        this.id = id;
        this.iconfont = iconfont;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIconfont() {
        return iconfont;
    }

    public void setIconfont(String iconfont) {
        this.iconfont = iconfont;
    }
}
