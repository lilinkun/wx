package com.android.wx.activity;

import android.view.View;

import com.android.wx.R;
import com.android.wx.adapter.TabPageAdapter;
import com.android.wx.base.activity.BaseActivity;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.db.DBManager;
import com.android.wx.event.EventCenter;
import com.android.wx.fragment.MenuFragment;
import com.android.wx.model.MenuTypeBean;
import com.android.wx.presenter.PreOrderPresenter;
import com.android.wx.view.IPreOrderView;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class PreOrderActivity extends MvpActivity<IPreOrderView, PreOrderPresenter> {


    @BindView(R.id.order_list_tablayou)
    SlidingTabLayout orderListTablayou;
    @BindView(R.id.order_list_vp)
    ViewPager orderListVp;


    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_pre_order;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {

        MenuTypeBean menuTypeBean = new MenuTypeBean();
        menuTypeBean.setMenuType(getString(R.string.tv_menu_type1));
        menuTypeBean.setMenuTypeId("1231");
//        DBManager.getInstance(this).insertMenuTypeBean(menuTypeBean);


        List<MenuTypeBean> menuTypeBeans = DBManager.getInstance(this).queryMenuTypeBean();

        ArrayList<String> mTitles = new ArrayList<>();

        mTitles.add(menuTypeBeans.get(0).getMenuType());

        List<Fragment> fragments = new ArrayList<>();

        MenuFragment menuFragment = new MenuFragment();

        fragments.add(menuFragment);

        TabPageAdapter pageAdapter = new TabPageAdapter(getSupportFragmentManager(), fragments, mTitles);
        orderListVp.setAdapter(pageAdapter);
        orderListTablayou.setViewPager(orderListVp);
        orderListVp.setCurrentItem(0, false);

    }

    @NonNull
    @Override
    public PreOrderPresenter createPresenter() {
        return new PreOrderPresenter();
    }
}
