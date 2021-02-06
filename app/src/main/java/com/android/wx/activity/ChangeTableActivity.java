package com.android.wx.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.wx.R;
import com.android.wx.adapter.HomeDataAdapter;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.IChangeTableView;
import com.android.wx.db.DBManager;
import com.android.wx.event.EventCenter;
import com.android.wx.model.Table;
import com.android.wx.presenter.ChangeTablePresenter;
import com.android.wx.utils.UToast;
import com.android.wx.view.ChangeTableDialog;
import com.android.wx.view.OrderInfoDialog;
import com.android.wx.weight.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName ChangeTableActivity
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/2 22:31
 */
public class ChangeTableActivity extends MvpActivity<IChangeTableView, ChangeTablePresenter> implements HomeDataAdapter.OnHomeDataClickListener {

    @BindView(R.id.sp_change_table)
    Spinner spinner;
    @BindView(R.id.recycler_table)
    RecyclerView tabList;


    final String[] spinnerItems = {"一楼","二楼","三楼"};
    String floor = "";
    List<Table> tables;
    Table mTable;
    HomeDataAdapter homeDataAdapter;


    @NonNull
    @Override
    public ChangeTablePresenter createPresenter() {
        return new ChangeTablePresenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_change_table;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {



    }

    public void initSp(){
        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.item_spinselect, spinnerItems);
        spinnerAdapter.setDropDownViewResource(R.layout.item_spinselect);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (homeDataAdapter != null && !floor.equals(spinnerItems[i])){
                    homeDataAdapter.setFloor(spinnerItems[i]);
                    floor = spinnerItems[i];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void initData() {

        mTable = (Table) getIntent().getSerializableExtra("table");

        listData();

        initSp();
    }


    public void listData() {
        tables = new ArrayList<>();
        tables = DBManager.getInstance(this).queryTable(spinnerItems[0]);
        floor = spinnerItems[0];
        homeDataAdapter = new HomeDataAdapter(this,spinnerItems[0]);
        homeDataAdapter.setListener(this);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 4);
        tabList.addItemDecoration(new SpaceItemDecoration(30, SpaceItemDecoration.GRIDLAYOUT));
        tabList.setLayoutManager(linearLayoutManager);
        tabList.setAdapter(homeDataAdapter);
    }

    @Override
    public void onItemClick(Table table) {
        if (table != null) {

            if (table.getTableNum().equals(mTable.getTableNum())){
                UToast.show(this,R.string.change_no_table);
            }else {

                if (table.getStatue().equals(getString(R.string.idle))) {

                    ChangeTableDialog changeTableDialog = new ChangeTableDialog(this,null);
                    changeTableDialog.show();

                } else {
                    OrderInfoDialog orderInfoDialog = new OrderInfoDialog(this, table.getOrderNumber());
                    orderInfoDialog.show();
                }
            }
        }
    }

    @OnClick({R.id.tv_change_table_exit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_change_table_exit:

                finish();


                break;
        }
    }
}
