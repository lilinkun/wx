package com.android.wx.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.HomePerenter;
import com.android.wx.contract.IHomeView;
import com.android.wx.view.HomeClockInDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kai
 * on 2021/1/14
 */
public class HomeActivity extends MvpActivity<IHomeView, HomePerenter>  {

    @BindView(R.id.tv_login_time)
    TextView tvLoginTime;
    @BindView(R.id.tv_login_date)
    TextView tvLoginDate;

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

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");

        tvLoginDate.setText(simpleDateFormat.format(date));
        tvLoginTime.setText(simpleDateFormat1.format(date));

        HomeClockInDialog homeClockInDialog = new HomeClockInDialog(this);
        homeClockInDialog.show();
    }

    @OnClick({R.id.btn_home_halleat,R.id.rl_home_delivery,R.id.rl_home_get,R.id.rl_vip,R.id.rl_find_order,R.id.rl_clock_in,R.id.rl_gift,R.id.rl_setting})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_home_halleat:

                Intent intent = new Intent(this,DiningHallActivity.class);
                startActivity(intent);

                break;

            case R.id.rl_home_delivery:

                Intent intent1 = new Intent(this,DeliveryActivity.class);
                startActivity(intent1);

                break;

            case R.id.rl_home_get:

                Intent intent2 = new Intent(this,HomeGetActivity.class);
                startActivity(intent2);
                break;

            case R.id.rl_vip:

                Intent intent3 = new Intent(this,VipActivity.class);
                startActivity(intent3);

                break;

            case R.id.rl_find_order:

                Intent intent4 = new Intent(this,SearchActivity.class);
                startActivity(intent4);

                break;

            case R.id.rl_clock_in:

                HomeClockInDialog homeClockInDialog = new HomeClockInDialog(this);
                homeClockInDialog.show();

                break;

            case R.id.rl_gift:

                Intent intent5 = new Intent(this,GiftActivity.class);
                startActivity(intent5);

                break;

            case R.id.rl_setting:

                Intent intent6 = new Intent(this,SettingActivity.class);
                startActivity(intent6);

                break;
        }
    }
}
