package com.android.wx.model;

import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.List;

/**
 * @ClassName MenuGroupBean
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/7 22:14
 */
public class MenuGroupBean implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<MenuInfo> {
    private String priceName;
    private List<MenuInfo> menuInfos;

    public MenuGroupBean(String priceName, List<MenuInfo> menuInfos) {
        this.priceName = priceName;
        this.menuInfos = menuInfos;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }


    @Override
    public int getChildCount() {
        return menuInfos.size();
    }

    @Override
    public MenuInfo getChildAt(int childIndex) {
        return menuInfos.get(childIndex);
    }

    public List<MenuInfo> getMenuInfos() {
        return menuInfos;
    }

    @Override
    public boolean isExpandable() {
        return true;
    }
}
