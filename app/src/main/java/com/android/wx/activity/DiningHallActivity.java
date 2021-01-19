package com.android.wx.activity;

import android.content.Intent;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.wx.R;
import com.android.wx.adapter.HomeDataAdapter;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.event.EventCenter;
import com.android.wx.model.Table;
import com.android.wx.presenter.DiningHallPersenter;
import com.android.wx.view.IDiningHallView;
import com.android.wx.weight.SpaceItemDecoration;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
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
        for (int i = 0; i < 20; i++) {
            table = new Table();
            tables.add(table);
        }

        HomeDataAdapter homeDataAdapter = new HomeDataAdapter(tables,this);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 4);
        tabList.addItemDecoration(new SpaceItemDecoration(30, SpaceItemDecoration.GRIDLAYOUT));
        tabList.setLayoutManager(linearLayoutManager);
        tabList.setAdapter(homeDataAdapter);


    }

    @OnClick({R.id.tv_home_start_preorder})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_home_start_preorder:

                Intent intent = new Intent();
                intent.setClass(this,PreOrderActivity.class);
                startActivity(intent);

                break;
        }
    }

}
