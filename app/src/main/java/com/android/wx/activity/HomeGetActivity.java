package com.android.wx.activity;

import android.view.View;

import com.android.wx.R;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.IHomeGetView;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.HomeGetPresenter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @ClassName HomeGetActivity
 * @Description TODO
 * @Author Administrator
 * @Date 2021/1/23 11:40
 */
public class HomeGetActivity extends MvpActivity<IHomeGetView, HomeGetPresenter> {

    @BindView(R.id.rv_home_get)
    RecyclerView rvHomeGet;


    @NonNull
    @Override
    public HomeGetPresenter createPresenter() {
        return new HomeGetPresenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_home_get;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {

    }
}
