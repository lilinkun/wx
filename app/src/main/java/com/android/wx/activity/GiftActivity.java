package com.android.wx.activity;

import android.view.View;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.adapter.GifeSearchAdapter;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.IGiftView;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.GiftPresenter;
import com.android.wx.weight.SpaceItemDecoration;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName GiftActivity
 * @Description TODO
 * @Author Administrator
 * @Date 2021/1/29 2:50
 */
public class GiftActivity extends MvpActivity<IGiftView, GiftPresenter> {

    @BindView(R.id.rv_gift)
    RecyclerView rvGift;
    @BindView(R.id.tv_login_time)
    TextView tvLoginTime;
    @BindView(R.id.tv_login_date)
    TextView tvLoginDate;

    @NonNull
    @Override
    public GiftPresenter createPresenter() {
        return new GiftPresenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_gift;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {


        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        tvLoginDate.setText(simpleDateFormat.format(date));
        tvLoginTime.setText(simpleDateFormat1.format(date));
        GifeSearchAdapter gifeSearchAdapter = new GifeSearchAdapter(this,null);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        rvGift.addItemDecoration(new SpaceItemDecoration(20, SpaceItemDecoration.GRIDLAYOUT));
        rvGift.setLayoutManager(gridLayoutManager);
        rvGift.setAdapter(gifeSearchAdapter);

    }

    @OnClick({R.id.tv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_back:

                finish();

                break;
        }
    }

}
