package com.android.wx.activity;

import android.view.View;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.IHomeGetView;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.HomeGetPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName HomeGetActivity
 * @Description TODO
 * @Author liguo
 * @Date 2021/1/23 11:40
 */
public class HomeGetActivity extends MvpActivity<IHomeGetView, HomeGetPresenter> {

    @BindView(R.id.rv_home_get)
    RecyclerView rvHomeGet;
    @BindView(R.id.tv_login_time)
    TextView tvLoginTime;
    @BindView(R.id.tv_login_date)
    TextView tvLoginDate;


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


        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");

        tvLoginDate.setText(simpleDateFormat.format(date));
        tvLoginTime.setText(simpleDateFormat1.format(date));
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
