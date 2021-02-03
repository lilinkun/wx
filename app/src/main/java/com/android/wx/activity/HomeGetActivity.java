package com.android.wx.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.adapter.HomeGetAdapter;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.IHomeGetView;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.HomeGetPresenter;
import com.android.wx.utils.IWxConstant;
import com.android.wx.utils.UToast;
import com.android.wx.weight.SpaceItemDecoration;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
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
    @BindView(R.id.tv_input_get_username)
    TextView tvInputGetUsername;
    @BindView(R.id.et_get_input_phone)
    EditText etGetInputPhone;
    @BindView(R.id.et_get_input_name)
    EditText etGetInputName;
    @BindView(R.id.et_get_remarks)
    EditText etGetRemarks;
    @BindView(R.id.rl_home_get_history)
    RelativeLayout rlHomeGetHistory;

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

    @OnClick({R.id.tv_back,R.id.tv_go_order,R.id.tv_home_get_history})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_back:

                finish();

                break;

            case R.id.tv_go_order:

                if (IWxConstant.isNull(etGetInputPhone) && IWxConstant.isNull(etGetInputName)){

                    Intent intent = new Intent(this,PreOrderActivity.class);
                    intent.putExtra("inputPhone",etGetInputPhone.getText().toString());
                    intent.putExtra("inputName",etGetInputName.getText().toString());
                    intent.putExtra("type","get");
                    startActivity(intent);

                }else {
                    UToast.show(this,R.string.get_incomplete);
                }



                break;

            case R.id.tv_home_get_history:

                if (IWxConstant.isNull(etGetInputPhone) && IWxConstant.isNull(etGetInputName)){

                    HomeGetAdapter homeGetAdapter = new HomeGetAdapter(this,null);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
                    rvHomeGet.addItemDecoration(new SpaceItemDecoration(20, SpaceItemDecoration.GRIDLAYOUT));
                    rvHomeGet.setLayoutManager(gridLayoutManager);

                    rvHomeGet.setAdapter(homeGetAdapter);

                    rlHomeGetHistory.setVisibility(View.VISIBLE);
                    tvInputGetUsername.setVisibility(View.GONE);

                }else {
                    UToast.show(this,R.string.get_incomplete);
                }

                break;
        }
    }
}
