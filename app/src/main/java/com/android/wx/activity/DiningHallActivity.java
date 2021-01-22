package com.android.wx.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.adapter.HomeDataAdapter;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.event.EventCenter;
import com.android.wx.model.Table;
import com.android.wx.presenter.DiningHallPersenter;
import com.android.wx.contract.IDiningHallView;
import com.android.wx.weight.SpaceItemDecoration;
import com.github.library.BaseQuickAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kai
 * on 2021/1/15
 * 堂食
 */
public class DiningHallActivity extends MvpActivity<IDiningHallView, DiningHallPersenter> {
    @BindView(R.id.recycler_table)
    RecyclerView tabList;
    BaseQuickAdapter baseQuickAdapter;
    Table table;
    @BindView(R.id.sp_dining_hall)
    Spinner spinner;
    @BindView(R.id.tv_home_status)
    TextView tvHomeStatus;
    @BindView(R.id.tv_home_price)
    TextView tvHomePrice;
    @BindView(R.id.tv_home_time_old)
    TextView tvHomeTimeOld;
    @BindView(R.id.tv_home_id)
    TextView tvHomeId;
    @BindView(R.id.tv_home_time)
    TextView tvHomeTime;
    @BindView(R.id.tv_home_number)
    TextView tvHomeNumber;
    @BindView(R.id.tv_home_customer_name)
    TextView tvHomeCustomerName;
    @BindView(R.id.tv_home_start_preorder)
    TextView tvHomeStartPreorder;
    @BindView(R.id.sw_hall)
    Switch swHall;

    private HomeDataAdapter homeDataAdapter;


    @NonNull
    @Override
    public DiningHallPersenter createPresenter() {
        return new DiningHallPersenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activiyt_dining_hall;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {

        listData();
        initSp();

        swHall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    setType(10);
                }else {
                    setType(9);
                }
            }
        });
    }


    public void initSp(){
        final String[] spinnerItems = {"一楼","二楼","三楼"};
        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.item_spinselect, spinnerItems);
        spinnerAdapter.setDropDownViewResource(R.layout.item_spinselect);
        spinner.setAdapter(spinnerAdapter);
    }
    public void listData() {
        ArrayList<Table> tables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            table = new Table(1231,"西红柿","88888888","4人",892,992,845,"空闲",12345,"88:55","张三","1楼");
            tables.add(table);
//            DBManager.getInstance(this).insertTable(table);
        }
        for (int i = 0; i < 10; i++) {
            table = new Table(1231,"西红柿222","6213121431","4人",892,992,845,"空闲",12345,"88:55","张三","2楼");
            tables.add(table);
//            DBManager.getInstance(this).insertTable(table);
        }

        homeDataAdapter = new HomeDataAdapter(tables,this);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 4);
        tabList.addItemDecoration(new SpaceItemDecoration(30, SpaceItemDecoration.GRIDLAYOUT));
        tabList.setLayoutManager(linearLayoutManager);
        tabList.setAdapter(homeDataAdapter);


    }

    @OnClick({R.id.tv_home_start_preorder,R.id.tv_home_status,R.id.tv_more,R.id.tv_home_price,R.id.tv_home_time_old,R.id.tv_home_id,R.id.tv_home_time,R.id.tv_home_number,R.id.tv_home_customer_name})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_home_start_preorder:

                unSelectBtn();

                tvHomeStartPreorder.setTextColor(getResources().getColor(R.color.text_yellow));
                tvHomeStartPreorder.setBackgroundResource(R.drawable.bg_home_text_bottom_select);

                Intent intent = new Intent();
                intent.setClass(this,PreOrderActivity.class);
                startActivity(intent);

                break;

            case R.id.tv_more:

                final String[] spinnerItems1 = getResources().getStringArray(R.array.more_array);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setItems(spinnerItems1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = spinnerItems1[i];
                        Log.i(TAG,name);
                    }
                }).show();

                break;

            case R.id.tv_home_status:
                unSelectBtn();
                tvHomeStatus.setTextColor(getResources().getColor(R.color.text_yellow));
                tvHomeStatus.setBackgroundResource(R.drawable.bg_home_text_bottom_select);

                setType(6);

                break;

            case R.id.tv_home_price:

                unSelectBtn();
                tvHomePrice.setTextColor(getResources().getColor(R.color.text_yellow));
                tvHomePrice.setBackgroundResource(R.drawable.bg_home_text_bottom_select);

                if (homeDataAdapter != null){
                    homeDataAdapter.setType(1);
                }

                break;

            case R.id.tv_home_time_old:

                unSelectBtn();
                tvHomeTimeOld.setTextColor(getResources().getColor(R.color.text_yellow));
                tvHomeTimeOld.setBackgroundResource(R.drawable.bg_home_text_bottom_select);

                setType(2);

                break;

            case R.id.tv_home_time:

                unSelectBtn();
                tvHomeTime.setTextColor(getResources().getColor(R.color.text_yellow));
                tvHomeTime.setBackgroundResource(R.drawable.bg_home_text_bottom_select);

                setType(4);

                break;

            case R.id.tv_home_customer_name:

                unSelectBtn();
                tvHomeCustomerName.setTextColor(getResources().getColor(R.color.text_yellow));
                tvHomeCustomerName.setBackgroundResource(R.drawable.bg_home_text_bottom_select);
                setType(8);
                break;

            case R.id.tv_home_id:

                unSelectBtn();
                tvHomeId.setTextColor(getResources().getColor(R.color.text_yellow));
                tvHomeId.setBackgroundResource(R.drawable.bg_home_text_bottom_select);

                setType(3);

                break;

            case R.id.tv_home_number:

                unSelectBtn();
                tvHomeNumber.setTextColor(getResources().getColor(R.color.text_yellow));
                tvHomeNumber.setBackgroundResource(R.drawable.bg_home_text_bottom_select);

                setType(5);

                break;


        }
    }


    private void setType(int type){
        if (homeDataAdapter != null){
            homeDataAdapter.setType(type);
        }
    }

    private void unSelectBtn(){

        tvHomeStartPreorder.setTextColor(getResources().getColor(R.color.text_bottom_666));
        tvHomeStartPreorder.setBackgroundResource(R.drawable.bg_home_text_bottom_unselect);
        tvHomeNumber.setTextColor(getResources().getColor(R.color.text_bottom_666));
        tvHomeNumber.setBackgroundResource(R.drawable.bg_home_text_bottom_unselect);
        tvHomeId.setTextColor(getResources().getColor(R.color.text_bottom_666));
        tvHomeId.setBackgroundResource(R.drawable.bg_home_text_bottom_unselect);
        tvHomeCustomerName.setTextColor(getResources().getColor(R.color.text_bottom_666));
        tvHomeCustomerName.setBackgroundResource(R.drawable.bg_home_text_bottom_unselect);
        tvHomeTime.setTextColor(getResources().getColor(R.color.text_bottom_666));
        tvHomeTime.setBackgroundResource(R.drawable.bg_home_text_bottom_unselect);
        tvHomePrice.setTextColor(getResources().getColor(R.color.text_bottom_666));
        tvHomePrice.setBackgroundResource(R.drawable.bg_home_text_bottom_unselect);
        tvHomeTimeOld.setTextColor(getResources().getColor(R.color.text_bottom_666));
        tvHomeTimeOld.setBackgroundResource(R.drawable.bg_home_text_bottom_unselect);
        tvHomeStatus.setTextColor(getResources().getColor(R.color.text_bottom_666));
        tvHomeStatus.setBackgroundResource(R.drawable.bg_home_text_bottom_unselect);
    }

}
