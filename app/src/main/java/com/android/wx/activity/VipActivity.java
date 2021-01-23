package com.android.wx.activity;

import android.view.View;

import com.android.wx.R;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.IVipView;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.VipPresenter;

import androidx.annotation.NonNull;

/**
 * @ClassName VipActivity
 * @Description TODO
 * @Author liguo
 * @Date 2021/1/23 23:02
 */
public class VipActivity extends MvpActivity<IVipView,VipPresenter> {


    @NonNull
    @Override
    public VipPresenter createPresenter() {
        return new VipPresenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {

    }
}
