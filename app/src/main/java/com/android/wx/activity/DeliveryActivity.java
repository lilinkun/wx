package com.android.wx.activity;

import android.util.Log;
import android.view.View;

import com.android.wx.R;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.IDeliveryView;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.DeliveryPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import butterknife.BindView;

/**
 * @ClassName DeliveryActivity
 * @Description TODO
 * @Author liguo
 * @Date 2021/1/22 20:35
 */
public class DeliveryActivity extends MvpActivity<IDeliveryView, DeliveryPresenter>{



    @NonNull
    @Override
    public DeliveryPresenter createPresenter() {
        return new DeliveryPresenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_delivery;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {

    }

}
