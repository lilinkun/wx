package com.android.wx.activity;

import android.view.View;

import com.android.wx.R;
import com.android.wx.adapter.SettingAdapter;
import com.android.wx.adapter.TabPageAdapter;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.ISettingView;
import com.android.wx.event.EventCenter;
import com.android.wx.fragment.SettingHomeFragment;
import com.android.wx.model.SettingChildBean;
import com.android.wx.model.SettingGroupBean;
import com.android.wx.presenter.SettingPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

/**
 * @ClassName SettingActivity
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/3 0:20
 */
public class SettingActivity extends MvpActivity<ISettingView, SettingPresenter> {

    @BindView(R.id.rv_setting)
    RecyclerView rvSetting;
    @BindView(R.id.vp_setting)
    ViewPager vpSetting;

    private List<SettingGroupBean> listDate;
    private List<Fragment> fragments = new ArrayList<>();

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
        rvSetting.setAdapter(new SettingAdapter(this,listDate));


        ArrayList<String> mTitles = new ArrayList<>();
        fragments.add(new SettingHomeFragment());
        mTitles.add("");


        TabPageAdapter pageAdapter = new TabPageAdapter(getSupportFragmentManager(), fragments, mTitles);
        vpSetting.setAdapter(pageAdapter);

    }
}
