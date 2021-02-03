package com.android.wx.fragment;

import android.content.Context;

import com.android.wx.R;
import com.android.wx.adapter.HomeSettingAdapter;
import com.android.wx.base.Fragment.BaseFragment;
import com.android.wx.model.SettingGroupBean;
import com.android.wx.weight.SpaceItemDecoration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @ClassName SettingHomeFragment
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/4 0:43
 */
public class SettingHomeFragment extends BaseFragment {

    @BindView(R.id.rv_home_setting)
    RecyclerView rvHomeSetting;

    private List<SettingGroupBean> listDate;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_setting_home;
    }

    @Override
    public void initEventAndData() {

        String jsonStr = getString(R.string.setting_json);

        listDate = new Gson().fromJson(jsonStr,new TypeToken<List<SettingGroupBean>>() {}.getType());

        HomeSettingAdapter homeSettingAdapter = new HomeSettingAdapter(getActivity(),listDate);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);

        rvHomeSetting.addItemDecoration(new SpaceItemDecoration(20, SpaceItemDecoration.GRIDLAYOUT));

        rvHomeSetting.setLayoutManager(gridLayoutManager);

        rvHomeSetting.setAdapter(homeSettingAdapter);

    }
}
