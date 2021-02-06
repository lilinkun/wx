package com.android.wx.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.security.spec.DSAPrivateKeySpec;

/**
 * @ClassName BusinessHoursBean
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/5 22:40
 */
@Entity
public class BusinessHoursBean {

    private String businessName;
    private String businessTime;
    private String businessWeek;
    private String businessOperate;
    @Unique
    private String businessId;
    @Id(autoincrement = true)
    Long id;

    @Generated(hash = 550095145)
    public BusinessHoursBean(String businessName, String businessTime, String businessWeek, String businessOperate, String businessId,
            Long id) {
        this.businessName = businessName;
        this.businessTime = businessTime;
        this.businessWeek = businessWeek;
        this.businessOperate = businessOperate;
        this.businessId = businessId;
        this.id = id;
    }

    @Generated(hash = 1383147855)
    public BusinessHoursBean() {
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getBusinessWeek() {
        return businessWeek;
    }

    public void setBusinessWeek(String businessWeek) {
        this.businessWeek = businessWeek;
    }

    public String getBusinessOperate() {
        return businessOperate;
    }

    public void setBusinessOperate(String businessOperate) {
        this.businessOperate = businessOperate;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
