package com.android.wx.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.IDeliveryView;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.DeliveryPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName DeliveryActivity
 * @Description TODO
 * @Author liguo
 * @Date 2021/1/22 20:35
 */
public class DeliveryActivity extends MvpActivity<IDeliveryView, DeliveryPresenter>{

    @BindView(R.id.tv_login_time)
    TextView tvLoginTime;
    @BindView(R.id.tv_login_date)
    TextView tvLoginDate;


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


        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");

        tvLoginDate.setText(simpleDateFormat.format(date));
        tvLoginTime.setText(simpleDateFormat1.format(date));
    }

    @OnClick({R.id.tv_delivery_back,R.id.tv_delivery_order})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_delivery_back:

                finish();

                break;

            case R.id.tv_delivery_order:

                Intent intent = new Intent(this,PreOrderActivity.class);
                intent.putExtra("type","delivery");
                startActivity(intent);

                break;
        }
    }


}
