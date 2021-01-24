package com.android.wx.activity;

import android.view.View;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.adapter.SearchAdapter;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.ISearchView;
import com.android.wx.event.EventCenter;
import com.android.wx.model.OrderInfoBean;
import com.android.wx.presenter.SearchPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName SearchActivity
 * @Description TODO
 * @Author liguo
 * @Date 2021/1/24 18:09
 */
public class SearchActivity extends MvpActivity<ISearchView, SearchPresenter> {

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

        ArrayList<OrderInfoBean> orderInfoBeans = new ArrayList<>();
        SearchAdapter searchAdapter = new SearchAdapter(this,orderInfoBeans);
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
