package com.android.wx.activity;

import android.view.View;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.adapter.SettingAdapter;
import com.android.wx.adapter.TabPageAdapter;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.ISettingView;
import com.android.wx.event.EventCenter;
import com.android.wx.fragment.RestaurantManageFragment;
import com.android.wx.fragment.SettingHomeFragment;
import com.android.wx.model.SettingChildBean;
import com.android.wx.model.SettingGroupBean;
import com.android.wx.presenter.SettingPresenter;
import com.android.wx.weight.CustomViewPager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName SettingActivity
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/3 0:20
 */
public class SettingActivity extends MvpActivity<ISettingView, SettingPresenter> implements BaseExpandableRecyclerViewAdapter.ExpandableRecyclerViewOnClickListener<SettingGroupBean, SettingChildBean> {

    @BindView(R.id.rv_setting)
    RecyclerView rvSetting;
    @BindView(R.id.vp_setting)
    CustomViewPager vpSetting;
    @BindView(R.id.tv_setting_home)
    TextView tvSettingHome;

    private List<SettingGroupBean> listDate;
    private List<Fragment> fragments = new ArrayList<>();
    private SettingAdapter settingAdapter;

    @NonNull
    @Override
    public SettingPresenter createPresenter() {
        return new SettingPresenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {


    }

    @Override
    public void initData() {

        String jsonStr = getString(R.string.setting_json);

        listDate = new Gson().fromJson(jsonStr,new TypeToken<List<SettingGroupBean>>() {}.getType());

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvSetting.setLayoutManager(layoutManager);
        rvSetting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvSetting.setItemAnimator(new DefaultItemAnimator());

        settingAdapter = new SettingAdapter(this,listDate);

        rvSetting.setAdapter(settingAdapter);

        settingAdapter.setListener(this);

        ArrayList<String> mTitles = new ArrayList<>();
        fragments.add(new SettingHomeFragment());
        fragments.add(new RestaurantManageFragment());
        for (int i = 0; i < fragments.size();i++){
            mTitles.add("");
        }

        TabPageAdapter pageAdapter = new TabPageAdapter(getSupportFragmentManager(), fragments, mTitles);
        vpSetting.setAdapter(pageAdapter);

    }


    @Override
    public boolean onGroupLongClicked(SettingGroupBean groupItem) {
        return false;
    }

    @Override
    public boolean onInterceptGroupExpandEvent(SettingGroupBean groupItem, boolean isExpand) {
        return false;
    }

    @Override
    public void onGroupClicked(SettingGroupBean groupItem) {

    }

    @Override
    public void onChildClicked(SettingGroupBean groupItem, SettingChildBean childItem) {
        settingAdapter.setClickItem(childItem.getId());
        tvSettingHome.setTextColor(getResources().getColor(R.color.white));
        switch (childItem.getId()){
            case 1:
                vpSetting.setCurrentItem(1);

                break;
        }
    }

    @OnClick({R.id.tv_setting_home})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_setting_home:

                vpSetting.setCurrentItem(0);
                settingAdapter.setClickItem(0);
                tvSettingHome.setTextColor(getResources().getColor(R.color.text_yellow_00));

                break;
        }
    }
}
