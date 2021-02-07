package com.android.wx.activity;

import android.view.View;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.IReportView;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.ReportPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName ReportActivity
 * @Description TODO
 * @Date 2021/1/27 15:28
 */
public class ReportActivity extends MvpActivity<IReportView, ReportPresenter> {

    @BindView(R.id.tv_login_time)
    TextView tvLoginTime;
    @BindView(R.id.tv_login_date)
    TextView tvLoginDate;

    @NonNull
    @Override
    public ReportPresenter createPresenter() {
        return new ReportPresenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_report;
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

    @OnClick({R.id.iv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:

                finish();

                break;
        }
    }
}
