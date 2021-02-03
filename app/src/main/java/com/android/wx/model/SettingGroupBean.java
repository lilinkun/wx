package com.android.wx.model;

import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName SettingGroupBean
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/3 14:03
 */
public class SettingGroupBean implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<SettingChildBean> {

    private List<SettingChildBean> subData;
    private String title;

    public SettingGroupBean(List<SettingChildBean> sub, String title ){
        subData=sub;
        this.title=title;
    }
    public String getTitle(){
        return title;
    }

    @Override
    public int getChildCount() {
        return subData.size();
    }

    @Override
    public SettingChildBean getChildAt(int childIndex) {
        return subData.get(childIndex);
    }

    public List<SettingChildBean> getSubData(){
        return subData;
    }

    @Override
    public boolean isExpandable() {
        return true;
    }

}
