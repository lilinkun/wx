package com.android.wx.activity;

import android.content.Intent;
import android.view.View;

import com.android.wx.R;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.HomePerenter;
import com.android.wx.view.IHomeView;

import androidx.annotation.NonNull;
import butterknife.OnClick;

/**
 * Created by kai
 * on 2021/1/14
 */
public class HomeActivity extends MvpActivity<IHomeView, HomePerenter>  {

    @NonNull
    @Override
    public HomePerenter createPresenter() {
        return new HomePerenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_home_halleat})
    public void onClick(View view){
        Intent intent = new Intent(this,DiningHallActivity.class);
        startActivity(intent);
    }
}
