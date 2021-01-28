package com.android.wx.activity;

import android.view.View;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.adapter.SearchAdapter;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.ISearchView;
import com.android.wx.db.DBManager;
import com.android.wx.event.EventCenter;
import com.android.wx.model.OrderInfoBean;
import com.android.wx.presenter.SearchPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName SearchActivity
 * @Description TODO
 * @Author liguo
 * @Date 2021/1/24 18:09
 */
public class SearchActivity extends MvpActivity<ISearchView, SearchPresenter> {

    @BindView(R.id.rv_search)
    RecyclerView rvSearch;


    @NonNull
    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {

        List<OrderInfoBean> orderInfoBeans = DBManager.getInstance(this).queryOrder();

        SearchAdapter searchAdapter = new SearchAdapter(this,orderInfoBeans);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        rvSearch.setLayoutManager(gridLayoutManager);

        rvSearch.setAdapter(searchAdapter);

    }

    @OnClick({R.id.tv_exit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_exit:

                finish();

                break;
        }
    }
}
